package com.annaalfiani.gmcapps

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.annaalfiani.gmcapps.fragments.HomeFragment
import com.annaalfiani.gmcapps.fragments.ProfilFragment
import com.annaalfiani.gmcapps.fragments.TicketFragment
import com.annaalfiani.gmcapps.utils.Utilities
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{ var navStatus = -1 }
    private var fragment : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if(savedInstanceState == null){
            navigation.selectedItemId = R.id.navigation_home
        }
        Thread(Runnable {
            if (Utilities.isFirstTime(this@MainActivity)) {
                runOnUiThread { startActivity(Intent(this@MainActivity, IntroActivity::class.java).also {
                    finish()
                })}
            }
        }).start()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.navigation_home -> {
                if(navStatus != 0){
                    fragment = HomeFragment()
                    navStatus = 0
                }
            }
            R.id.navigation_ticket -> {
                if(navStatus != 1){
                    fragment = TicketFragment()
                    navStatus = 1
                }
            }
            R.id.navigation_profil -> {
                if(navStatus != 2){
                    fragment = ProfilFragment()
                    navStatus = 2
                }
            }
        }
        if(fragment == null){
            navStatus = 0
            fragment = HomeFragment()
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.screen_container, fragment!!)
        fragmentTransaction.commit()
        true
    }
}
