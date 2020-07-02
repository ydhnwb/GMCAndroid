package com.annaalfiani.gmcapps.ui.order

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.Movie
import com.annaalfiani.gmcapps.models.MovieSchedule
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.content_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(toolbar)
        setupToolbar()
        fill()
    }

    private fun setupToolbar(){
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun fill(){
        getPassedMovie()?.let {
            order_movie_poster.load("https://www.greenscene.co.id/wp-content/uploads/2020/04/Money-Heist-1.jpg")
            order_movieName_textView.text = it.judul
            order_date_textView.text = getPassedSchedule()?.date.toString()
            order_hour_textView.text = getPassedSchedule()?.hour.toString()
            order_studio_textView.text = getPassedSchedule()?.studio?.nama.toString()
        }
    }

    private fun getPassedSchedule() = intent.getParcelableExtra<MovieSchedule>("SCHEDULE")
    private fun getPassedMovie() = intent.getParcelableExtra<Movie>("FILM")
}