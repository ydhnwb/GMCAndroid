package com.annaalfiani.gmcapps.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annaalfiani.gmcapps.R
import kotlinx.android.synthetic.main.activity_onbording__two.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class Onbording_TwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording__two)

        btn_home.setOnClickListener{
            val intent = Intent(this@Onbording_TwoActivity,
                Onbording_TreeActivity::class.java)
            startActivity(intent)
        }
    }
}
