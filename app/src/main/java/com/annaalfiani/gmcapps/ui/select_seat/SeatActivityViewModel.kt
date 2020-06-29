package com.annaalfiani.gmcapps.ui.select_seat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaalfiani.gmcapps.models.Movie
import com.annaalfiani.gmcapps.models.Kursi
import com.annaalfiani.gmcapps.utils.SingleLiveEvent

class SeatActivityViewModel : ViewModel(){
    private val state : SingleLiveEvent<SeatActivityState> = SingleLiveEvent()
    private val selectedMovie = MutableLiveData<Movie>()
    private val seatInfo = MutableLiveData<Kursi>()

    private fun setLoading(){ state.value =
        SeatActivityState.Loading(true)
    }
    private fun hideLoading(){ state.value =
        SeatActivityState.Loading(false)
    }
    private fun showToast(message: String){ state.value =
        SeatActivityState.ShowToast(
            message
        )
    }

    fun setSelectedFilm(movie: Movie){
        selectedMovie.postValue(movie)
    }

    fun setSeatInfo(seat: Kursi){
        seatInfo.value = seat
    }

    fun removeSelectedSeat(){
        seatInfo.postValue(null)
    }

    fun listenToSeatInfo() = seatInfo
    fun listenToState() = state
    fun listenToSelectedFilm() = selectedMovie
}

sealed class SeatActivityState {
    data class Loading(val isLoading: Boolean) : SeatActivityState()
    data class ShowToast(val message: String) : SeatActivityState()
}