package com.annaalfiani.gmcapps.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annaalfiani.gmcapps.Adapter.FilmAdapter
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.Film
import com.annaalfiani.gmcapps.viewmodels.FilmState
import com.annaalfiani.gmcapps.viewmodels.FilmViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(R.layout.fragment_home){
    private lateinit var filmViewModel: FilmViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        filmViewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        filmViewModel.listenToComingSoon().observe(viewLifecycleOwner, Observer { handleComingSoonMovies(it) })
        filmViewModel.listenToNowPlaying().observe(viewLifecycleOwner, Observer { handleNowPlayingMovies(it) })
        filmViewModel.listenToState().observer(viewLifecycleOwner, Observer { handleUIState(it) })
        filmViewModel.fetchComingSoonMovies()
        filmViewModel.fetchNowPlayingMovies()
    }

    private fun handleComingSoonMovies(it : List<Film>){
        view!!.rv_comingsoon.adapter?.let { adapter -> if(adapter is FilmAdapter){
            adapter.updateList(it)
        }}
    }

    private fun handleNowPlayingMovies(it : List<Film>){
        view!!.rv_nowplaying.adapter?.let { adapter -> if(adapter is FilmAdapter){
            adapter.updateList(it)
        }}
    }

    private fun handleUIState(it : FilmState){
        when(it){
            is FilmState.IsComingSoonLoading -> {
                if(it.state){
                    view!!.loading_coming_soon.visibility = View.VISIBLE
                }else{
                    view!!.loading_coming_soon.visibility = View.GONE
                }
            }
            is FilmState.IsNowPlayingLoading -> {
                if(it.state){
                    view!!.loading_now_playing.visibility = View.VISIBLE
                }else{
                    view!!.loading_now_playing.visibility = View.GONE
                }
            }
        }
    }

    private fun setupUI(){
        view!!.rv_nowplaying.apply {
            adapter = FilmAdapter(mutableListOf(), activity!!)
        }
        view!!.rv_comingsoon.apply {
            adapter = FilmAdapter(mutableListOf(), activity!!)
        }
    }
}