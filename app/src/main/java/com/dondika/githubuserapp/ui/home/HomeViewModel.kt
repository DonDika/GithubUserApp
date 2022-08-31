package com.dondika.githubuserapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import com.dondika.githubuserapp.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _listUser = MutableLiveData<List<UserItem>>()
    val listUser: LiveData<List<UserItem>> = _listUser


    fun searchUser(username: String) {
        val client = ApiConfig.getApiService().fetchUser(username)
        client.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                    Log.d("responViewModel", "onResponse: ${response.body()?.items}")
                } else {
                    Log.e("respon", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("respon", "onFailure: ${t.message.toString()}")
            }

        })
    }


}