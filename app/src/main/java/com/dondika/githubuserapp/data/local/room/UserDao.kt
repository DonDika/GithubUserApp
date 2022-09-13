package com.dondika.githubuserapp.data.local.room

import androidx.room.*
import com.dondika.githubuserapp.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM user_data ORDER BY username ASC")
    suspend fun getAllFavoriteListUser(): List<UserEntity>

    @Query("SELECT * FROM user_data WHERE username = :username")
    suspend fun getFavoriteUser(username: String): UserEntity


}