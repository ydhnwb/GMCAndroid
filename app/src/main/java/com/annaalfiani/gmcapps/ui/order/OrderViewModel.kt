package com.annaalfiani.gmcapps.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaalfiani.gmcapps.models.Kursi
import com.annaalfiani.gmcapps.repositories.MovieRepository
import com.annaalfiani.gmcapps.repositories.SeatRepository
import com.annaalfiani.gmcapps.utils.SingleLiveEvent
import com.annaalfiani.gmcapps.utils.SingleResponse

class OrderViewModel (private val movieRepo: MovieRepository, private val seatRepository: SeatRepository) : ViewModel(){
    private val state: SingleLiveEvent<OrderState> = SingleLiveEvent()
    private val seats = MutableLiveData<Kursi>()
    private val selectedSeats = MutableLiveData<List<String>>()

    private fun setLoading(){ state.value = OrderState.Loading(true) }
    private fun hideLoading(){ state.value = OrderState.Loading(false) }
    private fun toast(message: String){ state.value = OrderState.ShowToast(message) }
    private fun alert(message: String){ state.value = OrderState.Alert(message) }
    private fun successOrder(){ state.value = OrderState.Success }


    fun fetchSeats(token: String, date: String, hour: String, studioId : String){
        setLoading()
        seatRepository.getAvailableSeats(token, studioId, hour, date, object : SingleResponse<Kursi>{
            override fun onSuccess(data: Kursi?) {
                hideLoading()
                data?.let { seats.postValue(it) }
            }
            override fun onFailure(err: Error) {
                hideLoading()
                err.message?.let { toast(it) }
            }
        })
    }

    fun getState() = state
    fun getSeats() = seats
    fun getSelectedSeats() = selectedSeats


}

sealed class OrderState {
    object Success : OrderState()
    data class Alert(val message: String) : OrderState()
    data class Loading(val isLoading: Boolean) : OrderState()
    data class ShowToast(val message: String) : OrderState()
}