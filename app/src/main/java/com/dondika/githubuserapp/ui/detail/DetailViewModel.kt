package com.dondika.githubuserapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.remote.response.DetailResponse
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _userData = MutableLiveData<DetailResponse>()
    val userData: LiveData<DetailResponse> = _userData

    fun getDetailUser(username: String){
        val client = ApiConfig.getApiService().getUsersDetail(username)
        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                _userData.value = response.body()
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

            }
        })
    }

}