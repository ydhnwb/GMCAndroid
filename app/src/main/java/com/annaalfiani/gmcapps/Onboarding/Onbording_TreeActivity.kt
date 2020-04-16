package com.annaalfiani.gmcapps.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annaalfiani.gmcapps.MainActivity
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.Sign_inActivity
import kotlinx.android.synthetic.main.activity_onbording__tree.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class Onbording_TreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording__tree)

        btn_home.setOnClickListener{
            finishAffinity()

            val intent = Intent(this@Onbording_TreeActivity,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}