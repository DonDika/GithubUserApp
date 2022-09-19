package com.dondika.githubuserapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.data.repository.UserRepository
import com.dondika.githubuserapp.utils.Result

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun searchUser(username: String): LiveData<Result<List<UserModel>>> {
        return userRepository.searchUser(username)
    }

}