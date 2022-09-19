package com.dondika.githubuserapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.dondika.githubuserapp.data.repository.UserRepository

class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {

    suspend fun getAllFavoritesUser() = userRepository.getAllFavoritesUser()

}