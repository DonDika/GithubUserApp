package com.dondika.githubuserapp.di

import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import com.dondika.githubuserapp.data.repository.UserRepository

object Injection {

    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }

}