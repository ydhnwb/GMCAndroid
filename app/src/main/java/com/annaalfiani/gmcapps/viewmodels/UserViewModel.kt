package com.annaalfiani.gmcapps.viewmodels

import androidx.lifecycle.ViewModel
import com.annaalfiani.gmcapps.models.User
import com.annaalfiani.gmcapps.utils.SingleLiveEvent
import com.annaalfiani.gmcapps.utils.Utilities
import com.annaalfiani.gmcapps.webservices.ApiCllient
import com.annaalfiani.gmcapps.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel(){
    private var api = ApiCllient.instance()
    private var state : SingleLiveEvent<UserState> = SingleLiveEvent()

    fun listenUIState() = state

    fun login(email : String, password : String) {
        state.value = UserState.IsLoading(true)
        api.login(email, password). enqueue(object : Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                println(t.message)
                state.value = UserState.ShowToast(t.message.toString())
                state.value = UserState.IsLoading(false)

            }

            override fun onResponse(call: Call<WrappedResponse<User>>, response: Response<WrappedResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        if(it.status.equals("1")){
                            state.value = UserState.Success(it.data.token!!)
                        }else{
                            state.value = UserState.ShowToast("Login Gagal")
                        }
                    }

                }else{
                    state.value = UserState.ShowToast("Kesalahan saat mengambil respon")
                }
                state.value = UserState.IsLoading(false)
            }


        })

    }

    fun validateLogin(email: String, password: String) : Boolean{
        state.value = UserState.Reset
        if (!Utilities.isValidEmail(email)){
            state.value = UserState.Validate(email = "Email Tidak Valid")
            return false
        }

        if (!Utilities.isValidPassword(password)){
            state.value = UserState.Validate(password = "Password Tidak Valid")
            return false
        }
        return true


    }


}

sealed class UserState {
    object Reset : UserState()
    data class IsLoading(var state: Boolean) : UserState()
    data class ShowToast(var message: String) : UserState()
    data class Validate(var email: String? = null,
                        var password: String? = null) : UserState()


    data class Success(var param : String) : UserState()

}