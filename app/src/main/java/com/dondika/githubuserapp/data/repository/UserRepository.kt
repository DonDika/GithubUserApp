package com.dondika.githubuserapp.data.repository

import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val apiService: ApiService
) {

    fun fetchSearchUser(username: String) {
        val client = apiService.fetchUser(username)
        client.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }




}