package com.annaalfiani.gmcapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annaalfiani.gmcapps.utils.Utilities

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        Thread(Runnable {
//            if(Utilities.getToken(this@HomeActivity) == null){
//                startActivity(Intent(this@HomeActivity,Sign_inActivity::class.java))
//                finish()
//            }
//         }).start()
    }

}
