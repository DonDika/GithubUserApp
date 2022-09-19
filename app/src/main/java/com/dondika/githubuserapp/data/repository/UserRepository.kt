package com.dondika.githubuserapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dondika.githubuserapp.data.local.datastore.SettingPreferences
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.data.local.room.UserDao
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.retrofit.ApiService
import com.dondika.githubuserapp.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val pref: SettingPreferences
){

    fun searchUser(username: String): LiveData<Result<List<UserModel>>> {
        val listUser = MutableLiveData<Result<List<UserModel>>>()
        listUser.value = Result.Loading
        val client = apiService.searchUser(username)
        client.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful){
                    val responseList = response.body()?.items
                    listUser.value = Result.Success(responseList)
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                listUser.value = Result.Error(t.message.toString())
            }
        })
        return listUser
    }

    suspend fun getDetailUser(username: String): LiveData<Result<UserModel>> {
        val detailUser = MutableLiveData<Result<UserModel>>()
        if (userDao.getFavoriteUser(username) != null){
            detailUser.value = Result.Success(userDao.getFavoriteUser(username))
        } else {
            val client = apiService.getDetailUser(username)
            client.enqueue(object : Callback<UserModel>{
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    val responseDetail = response.body()
                    detailUser.value = Result.Success(responseDetail)
                }
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    detailUser.value = Result.Error(t.message.toString())
                }
            })
        }
        return detailUser
    }

    fun getFollowers(username: String): LiveData<Result<List<UserModel>>> {
        val listUser = MutableLiveData<Result<List<UserModel>>>()
        listUser.value = Result.Loading
        val client = apiService.getFollowersUser(username)
        client.enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.isSuccessful){
                    val responseList = response.body()
                    listUser.value = Result.Success(responseList)
                }
            }
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                listUser.value = Result.Error(t.message.toString())
            }
        })
        return listUser
    }

    fun getFollowing(username: String): LiveData<Result<List<UserModel>>> {
        val listUser = MutableLiveData<Result<List<UserModel>>>()
        listUser.value = Result.Loading
        val client = apiService.getFollowingUser(username)
        client.enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.isSuccessful){
                    val responseList = response.body()
                    listUser.value = Result.Success(responseList)
                }
            }
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                listUser.value = Result.Error(t.message.toString())
            }
        })
        return listUser
    }

    suspend fun getAllFavoritesUser(): LiveData<Result<List<UserModel>>> {
        val listFavorites = MutableLiveData<Result<List<UserModel>>>()
        listFavorites.value = Result.Loading
        if (userDao.getAllFavoriteListUser().isEmpty()){
            listFavorites.value = Result.Error(null)
        } else {
            listFavorites.value = Result.Success(userDao.getAllFavoriteListUser())
        }
        return listFavorites
    }

    suspend fun saveAsFavorites(userModel: UserModel) = userDao.insert(userModel)

    suspend fun deleteFromFavorites(userModel: UserModel) = userDao.delete(userModel)

    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        pref.saveThemeSetting(isDarkModeActive)
    }

    fun getThemeSetting() = pref.getThemeSetting()

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao,
            pref: SettingPreferences
        ): UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService, userDao, pref)
            }.also { instance = it }
    }

}