package com.dondika.githubuserapp.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    suspend fun getDetailUser(username: String) = userRepository.getDetailUser(username)

    fun saveAsFavorites(userModel: UserModel) {
        viewModelScope.launch {
            userRepository.saveAsFavorites(userModel)
        }
    }

    fun deleteFromFavorites(userModel: UserModel) {
        viewModelScope.launch {
            userRepository.deleteFromFavorites(userModel)
        }
    }

}