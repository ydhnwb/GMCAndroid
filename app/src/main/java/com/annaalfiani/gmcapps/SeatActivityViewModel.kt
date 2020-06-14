package com.annaalfiani.gmcapps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaalfiani.gmcapps.models.Film
import com.annaalfiani.gmcapps.models.Kursi
import com.annaalfiani.gmcapps.utils.SingleLiveEvent

class SeatActivityViewModel : ViewModel(){
    private val state : SingleLiveEvent<SeatActivityState> = SingleLiveEvent()
    private val selectedMovie = MutableLiveData<Film>()
    private val seatInfo = MutableLiveData<Kursi>()

    private fun setLoading(){ state.value = SeatActivityState.Loading(true) }
    private fun hideLoading(){ state.value = SeatActivityState.Loading(false) }
    private fun showToast(message: String){ state.value = SeatActivityState.ShowToast(message) }

    fun setSelectedFilm(film: Film){
        selectedMovie.postValue(film)
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