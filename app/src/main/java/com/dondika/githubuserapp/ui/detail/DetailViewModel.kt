package com.dondika.githubuserapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.remote.response.DetailResponse
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<DetailResponse>()
    val userData: LiveData<DetailResponse> = _userData

    fun getDetailUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                _isLoading.value = false
                _userData.value = response.body()
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }

}