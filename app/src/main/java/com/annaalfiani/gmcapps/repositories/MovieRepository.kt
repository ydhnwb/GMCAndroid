package com.annaalfiani.gmcapps.repositories

import com.annaalfiani.gmcapps.models.Movie
import com.annaalfiani.gmcapps.utils.ArrayResponse
import com.annaalfiani.gmcapps.webservices.ApiService
import com.annaalfiani.gmcapps.webservices.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MovieContract {
    fun comingSoonMovies(listener: ArrayResponse<Movie>)
    fun nowPlayingMovies(listener: ArrayResponse<Movie>)
}

class MovieRepository (private val api: ApiService) : MovieContract {
    override fun comingSoonMovies(listener: ArrayResponse<Movie>) {
        api.moviesComingSoon().enqueue(object: Callback<WrappedListResponse<Movie>>{
            override fun onFailure(call: Call<WrappedListResponse<Movie>>, t: Throwable) {
                println(t.message)
                listener.onFailure(Error(t.message))
            }

            override fun onResponse(call: Call<WrappedListResponse<Movie>>, response: Response<WrappedListResponse<Movie>>) {
                when{
                    response.isSuccessful -> listener.onSuccess(response.body()!!.data)
                    !response.isSuccessful -> listener.onFailure(Error(response.message()))
                }
            }
        })
    }

    override fun nowPlayingMovies(listener: ArrayResponse<Movie>) {
        api.moviesNowPlaying().enqueue(object: Callback<WrappedListResponse<Movie>>{
            override fun onFailure(call: Call<WrappedListResponse<Movie>>, t: Throwable) {
                println(t.message)
                listener.onFailure(Error(t.message))
            }

            override fun onResponse(call: Call<WrappedListResponse<Movie>>, response: Response<WrappedListResponse<Movie>>) {
                when{
                    response.isSuccessful -> listener.onSuccess(response.body()!!.data)
                    !response.isSuccessful -> listener.onFailure(Error(response.message()))
                }
            }
        })
    }

}