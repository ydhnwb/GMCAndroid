package com.annaalfiani.gmcapps.ui.detail_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.Movie
import com.annaalfiani.gmcapps.models.MovieSchedule
import com.annaalfiani.gmcapps.ui.select_seat.SeatActivity
import com.annaalfiani.gmcapps.utils.Utilities
import com.annaalfiani.gmcapps.utils.extensions.gone
import com.annaalfiani.gmcapps.utils.extensions.showInfoAlert
import com.annaalfiani.gmcapps.utils.extensions.toast
import com.annaalfiani.gmcapps.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), DetailMovieInterface {
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupScheduleRecycler()
        observe()

    }

    private fun setupScheduleRecycler(){
        detail_schedule_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = MovieScheduleAdapter(mutableListOf(), this@DetailActivity)
        }
    }


    private fun getPassedMovie() : Movie? = intent.getParcelableExtra("FILM")

    private fun observe(){
        observeState()
        observeMovie()
        observeSchedules()
    }

    private fun observeSchedules() = detailViewModel.getSchedules().observe(this, Observer { handleSchedules(it) })
    private fun observeState() = detailViewModel.getState().observer(this, Observer { handleState(it) })
    private fun observeMovie() = detailViewModel.getMovie().observe(this, Observer { handleMovie(it) })

    private fun handleMovie(movie: Movie){
        fill(movie)
    }

    private fun handleSchedules(schedules: List<MovieSchedule>){
        detail_schedule_recyclerView.adapter?.let { adapter ->
            if(adapter is MovieScheduleAdapter){
                adapter.updateList(schedules)
            }
        }
    }

    private fun fill(movie: Movie){
        detail_movie_img.load("https://gmcweb.herokuapp.com/uploads/admin/"+movie.foto)
        detail_movie_title.text = movie.judul
        detail_movie_genre.text = movie.genre
        sinopsis.text = movie.sinopsis
        detail_movie_tanggal.text = "${getPassedMovie()?.jadwalTayang!!.tanggalMulai} - ${getPassedMovie()?.jadwalTayang!!.tanggalSelesai}"
        detail_movie_jam.text = getPassedMovie()?.jadwalTayang!!.jam
    }

    private fun handleState(state: DetailState){
        when(state){
            is DetailState.Loading -> isLoading(state.isLoading)
            is DetailState.ShowToast -> toast(state.message)
        }
    }

    private fun isLoading(b: Boolean){
        if(b){
            loading.visible()
        }else{
            loading.gone()
        }
    }

    override fun itemClick(movie: MovieSchedule) {
        Utilities.getToken(this)?.let {
            //go to order activity
        } ?: kotlin.run {
            showInfoAlert(resources.getString(R.string.please_login))
        }
    }
}
