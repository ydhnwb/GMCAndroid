package com.annaalfiani.gmcapps.ui.order

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.annaalfiani.gmcapps.R
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(toolbar)
        setupToolbar()
    }

    private fun setupToolbar(){
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener { finish() }
    }
}