package com.annaalfiani.gmcapps.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaalfiani.gmcapps.models.Film
import com.annaalfiani.gmcapps.utils.SingleLiveEvent
import com.annaalfiani.gmcapps.webservices.ApiCllient
import com.annaalfiani.gmcapps.webservices.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel(){
    private val api = ApiCllient.instance()
    private val state : SingleLiveEvent<FilmState> = SingleLiveEvent()
    private val comingSoonMovies = MutableLiveData<List<Film>>()
    private val nowPlayingMovies = MutableLiveData<List<Film>>()

    private fun setNowPlayingLoading() { state.value = FilmState.IsNowPlayingLoading(true) }
    private fun hideNowPlayingLoading() { state.value = FilmState.IsNowPlayingLoading(false) }
    private fun setComingSoonLoading() { state.value = FilmState.IsComingSoonLoading(true) }
    private fun hideComingSoonLoading() { state.value = FilmState.IsComingSoonLoading(false) }
    private fun toast(message: String) { state.value = FilmState.ShowToast(message) }


    fun fetchComingSoonMovies() {
        setComingSoonLoading()
        api.moviesComingSoon().enqueue(object : Callback<WrappedListResponse<Film>>{
            override fun onFailure(call: Call<WrappedListResponse<Film>>, t: Throwable) {
                println(t.message)
                hideComingSoonLoading()
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<WrappedListResponse<Film>>, response: Response<WrappedListResponse<Film>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    comingSoonMovies.postValue(body?.data)
                }
                hideComingSoonLoading()
            }
        })
    }

    fun fetchNowPlayingMovies() {
        setNowPlayingLoading()
        api.moviesNowPlaying().enqueue(object : Callback<WrappedListResponse<Film>>{
            override fun onFailure(call: Call<WrappedListResponse<Film>>, t: Throwable) {
                println(t.message)
                hideNowPlayingLoading()
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<WrappedListResponse<Film>>, response: Response<WrappedListResponse<Film>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    nowPlayingMovies.postValue(body?.data)
                }
                hideNowPlayingLoading()
            }
        })
    }

    fun listenToComingSoon() = comingSoonMovies
    fun listenToState() = state
    fun listenToNowPlaying() = nowPlayingMovies
}

sealed class FilmState {
    data class IsComingSoonLoading(var state : Boolean) : FilmState()
    data class IsNowPlayingLoading(var state : Boolean) : FilmState()
    data class ShowToast(var message : String) : FilmState()
}