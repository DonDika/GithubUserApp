package com.dondika.githubuserapp.data.remote.retrofit

import com.dondika.githubuserapp.BuildConfig
import com.dondika.githubuserapp.data.remote.response.DetailResponse
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users?")
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    fun searchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<List<UserItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<List<UserItem>>




}