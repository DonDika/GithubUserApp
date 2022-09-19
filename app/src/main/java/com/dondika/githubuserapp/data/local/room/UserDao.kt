package com.dondika.githubuserapp.data.local.room

import androidx.room.*
import com.dondika.githubuserapp.data.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user_data ORDER BY username ASC")
    suspend fun getAllFavoriteListUser(): List<UserModel>

    @Query("SELECT * FROM user_data WHERE username = :username")
    suspend fun getFavoriteUser(username: String): UserModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: UserModel)

    @Delete
    suspend fun delete(users: UserModel)
}

