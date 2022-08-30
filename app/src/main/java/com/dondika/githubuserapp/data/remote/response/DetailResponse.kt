package com.dondika.githubuserapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("login")
	val username: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

)
