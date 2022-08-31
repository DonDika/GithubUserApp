package com.dondika.githubuserapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
	@field:SerializedName("items")
	val items: List<UserItem>
)

data class UserItem(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("login")
	val username: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

)
