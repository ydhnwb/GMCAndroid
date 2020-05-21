package com.annaalfiani.gmcapps.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annaalfiani.gmcapps.MainActivity
import com.annaalfiani.gmcapps.R
import kotlinx.android.synthetic.main.activity_onboarding_one.*
import kotlinx.android.synthetic.main.activity_sign_in.btn_daftar

class OnboardingOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        btn_home.setOnClickListener{
            val intent = Intent(this@OnboardingOneActivity,
                Onbording_TwoActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener{
            finishAffinity()

            val intent = Intent(this@OnboardingOneActivity,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}
