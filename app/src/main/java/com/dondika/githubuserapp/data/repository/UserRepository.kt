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
        apiService.fetchUser(username)
    }


    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(apiService: ApiService): UserRepository {
            return INSTANCE ?: synchronized(this) {
                UserRepository(apiService).also {
                    INSTANCE = it
                }
            }
        }
    }



}