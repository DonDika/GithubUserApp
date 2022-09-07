package com.dondika.githubuserapp.ui.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {

    private val _listFollowers = MutableLiveData<List<UserItem>>()
    val listFollowers: LiveData<List<UserItem>> = _listFollowers

    private val _listFollowing = MutableLiveData<List<UserItem>>()
    val listFollowing: LiveData<List<UserItem>> = _listFollowing


    fun getFollowersUser(username: String) {
        val client = ApiConfig.getApiService().getFollowersUser(username)
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
                _listFollowers.value = response.body()
            }
            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {

            }
        })
    }

    fun getFollowingUser(username: String) {
        val client = ApiConfig.getApiService().getFollowingUser(username)
        client.enqueue(object : Callback<List<UserItem>>{
            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
                _listFollowing.value = response.body()
            }
            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                Log.e("FollowViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }

}