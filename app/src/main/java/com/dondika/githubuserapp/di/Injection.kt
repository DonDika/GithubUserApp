package com.dondika.githubuserapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dondika.githubuserapp.data.local.datastore.SettingPreferences
import com.dondika.githubuserapp.data.local.room.UserDatabase
import com.dondika.githubuserapp.data.remote.retrofit.ApiConfig
import com.dondika.githubuserapp.data.repository.UserRepository

object Injection {

    fun provideRepository(context: Context, dataStore: DataStore<Preferences>): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        val pref = SettingPreferences.getInstance(dataStore)
        return UserRepository.getInstance(apiService, dao, pref)
    }

}