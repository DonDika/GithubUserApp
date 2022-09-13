package com.dondika.githubuserapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
class UserEntity (

    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "username")
    val username: String? = null,

    @field:ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @field:ColumnInfo(name = "html_url")
    val htmlUrl: String? = null,

    @field:ColumnInfo(name = "name")
    val name: String? = null,

    @field:ColumnInfo(name = "public_repos")
    val publicRepos: Int? = null,

    @field:ColumnInfo(name = "following")
    val following: Int? = null,

    @field:ColumnInfo(name = "followers")
    val followers: Int? = null,

    @field:ColumnInfo(name = "company")
    val company: String? = null,

    @field:ColumnInfo(name = "location")
    val location: String? = null,

    @field:ColumnInfo(name = "favorited")
    val isFavorited: Boolean

)