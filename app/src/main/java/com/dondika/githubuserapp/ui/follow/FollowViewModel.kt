package com.dondika.githubuserapp.ui.follow

import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.repository.UserRepository

class FollowViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFollowers(username: String) = userRepository.getFollowers(username)

    fun getFollowing(username: String) = userRepository.getFollowing(username)

}