package com.annaalfiani.gmcapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import coil.api.load
import com.annaalfiani.gmcapps.models.Film
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_movie_img.load("https://gmcweb.herokuapp.com/uploads/admin/"+getPassedMovie()?.foto)
        detail_movie_title.text = getPassedMovie()?.judul
        detail_movie_genre.text = getPassedMovie()?.genre
        sinopsis.text = getPassedMovie()?.sinopsis
        detail_movie_tanggal.text = "${getPassedMovie()?.jadwalTayang!!.tanggalMulai} - ${getPassedMovie()?.jadwalTayang!!.tanggalSelesai}"
        detail_movie_jam.text = getPassedMovie()?.jadwalTayang!!.jam

        btn_seat.setOnClickListener {
            startActivity(Intent(this@DetailActivity, SeatActivity::class.java).apply {
                getPassedMovie()?.let { movie ->
                    putExtra("seat_info", movie.jadwalTayang?.studio?.kursi)
                }
            })
        }
    }

    private fun getPassedMovie() : Film? = intent.getParcelableExtra("FILM")
}
