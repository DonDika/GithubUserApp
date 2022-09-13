package com.dondika.githubuserapp.data.repository

import com.dondika.githubuserapp.data.local.entity.UserEntity
import com.dondika.githubuserapp.data.local.room.UserDao
import com.dondika.githubuserapp.data.remote.retrofit.ApiService

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    //private val appExecutors: AppExe

){



    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao
        ): UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService, userDao)
            }.also { instance = it }
    }

}