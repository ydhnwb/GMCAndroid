package com.annaalfiani.gmcapps.sign.sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annaalfiani.gmcapps.Home.HomeActivity
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.utils.Utilities
import com.annaalfiani.gmcapps.viewmodels.UserState
import com.annaalfiani.gmcapps.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class Sign_inActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        doLogin()
        userViewModel.listenUIState().observer(this, Observer {
            handleState(it)
        })
    }

    private fun doLogin(){
        btn_login.setOnClickListener {
            val email = et_username.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (userViewModel.validateLogin(email, password)) {
                userViewModel.login(email, password)
            }
        }
    }

    private fun setErrorEmail(err : String?) { in_username.error = err }
    private fun setErrorPassword(err : String?) { in_password.error = err }
    private fun handleState(it: UserState) {
        when(it){
            is UserState.ShowToast -> Toast.makeText(this@Sign_inActivity, it.message, Toast.LENGTH_SHORT).show()
            is UserState.IsLoading -> btn_login.isEnabled = !it.state
            is UserState.Reset -> {
                setErrorEmail(null)
                setErrorPassword(null)
            }
            is UserState.Validate -> {
                it.email?.let { e -> setErrorEmail(e) }
                it.password?.let { e -> setErrorPassword(e) }
            }
            is UserState.Success -> {
                Utilities.setToken(this@Sign_inActivity, it.param)
                startActivity(Intent(this@Sign_inActivity, HomeActivity::class.java))
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(Utilities.getToken(this@Sign_inActivity) != null){
            startActivity(Intent(this@Sign_inActivity, HomeActivity::class.java))
            finish()
        }
    }
}
