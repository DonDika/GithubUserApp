package com.dondika.githubuserapp.di

import android.content.Context
import com.dondika.githubuserapp.data.local.room.UserDatabase
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import com.dondika.githubuserapp.data.repository.UserRepository

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(apiService, dao)

    }

}