package com.dondika.githubuserapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_data")
data class UserModel (

    @field:SerializedName("id")
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,

    @field:SerializedName("login")
    @field:ColumnInfo(name = "username")
    val username: String,

    @field:SerializedName("avatar_url")
    @field:ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = "",

    @field:SerializedName("html_url")
    @field:ColumnInfo(name = "html_url")
    val htmlUrl: String,

    @field:SerializedName("name")
    @field:ColumnInfo(name = "name")
    val name: String? = null,

    @field:SerializedName("public_repos")
    @field:ColumnInfo(name = "public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("following")
    @field:ColumnInfo(name = "following")
    val following: Int? = null,

    @field:SerializedName("followers")
    @field:ColumnInfo(name = "followers")
    val followers: Int? = null,

    @field:SerializedName("company")
    @field:ColumnInfo(name = "company")
    val company: String? = null,

    @field:SerializedName("location")
    @field:ColumnInfo(name = "location")
    val location: String? = null,

    @field:ColumnInfo(name = "favorites")
    var isFavorites: Boolean? = false

)