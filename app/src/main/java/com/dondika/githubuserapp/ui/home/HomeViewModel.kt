package com.dondika.githubuserapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listUser = MutableLiveData<List<UserItem>>()
    val listUser: LiveData<List<UserItem>> = _listUser

    fun searchUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUser(username)
        client.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                } else {
                    Log.e("HomeViewModel", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }

}