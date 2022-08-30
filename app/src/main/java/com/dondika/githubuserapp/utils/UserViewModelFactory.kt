package com.dondika.githubuserapp.utils

import androidx.lifecycle.ViewModelProvider
import com.dondika.githubuserapp.data.repository.UserRepository
import com.dondika.githubuserapp.di.Injection

class UserViewModelFactory private constructor(private val userRepository: UserRepository)
    : ViewModelProvider.NewInstanceFactory() {


    companion object {
        @Volatile
        private var INSTANCE: UserViewModelFactory? = null
        fun getInstance(): UserViewModelFactory {
            return INSTANCE ?: synchronized(this){
                UserViewModelFactory(Injection.provideRepository())
            }.also {
                INSTANCE = it
            }

        }
    }

}