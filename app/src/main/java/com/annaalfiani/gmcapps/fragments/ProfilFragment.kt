package com.annaalfiani.gmcapps.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.extenstions.gone
import com.annaalfiani.gmcapps.extenstions.visible
import com.annaalfiani.gmcapps.models.User
import com.annaalfiani.gmcapps.viewmodels.UserState
import com.annaalfiani.gmcapps.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment(R.layout.fragment_profil){

    private lateinit var userViewModel : UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.listenToState().observer(viewLifecycleOwner, Observer { handleUI(it) })
        userViewModel.listenToUser().observe(viewLifecycleOwner, Observer { handleUser(it) })
    }

    private fun handleUI(it : UserState){
        when(it){
            is UserState.IsLoading -> {
                if (it.state){
                    pb_profile.visible()
                    pb_profile.isIndeterminate = true
                }else{
                    pb_profile.gone()
                    pb_profile.isIndeterminate = false
                }
            }
            is UserState.ShowToast -> toast(it.message)
        }
    }

    private fun handleUser(it : User){
        tv_nama.text = it.name
        tv_email.text = it.email
    }

    private fun toast(message : String) { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
}