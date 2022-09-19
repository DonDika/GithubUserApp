package com.dondika.githubuserapp.data.remote.response

import com.dondika.githubuserapp.data.model.UserModel
import com.google.gson.annotations.SerializedName

data class SearchResponse(
	@field:SerializedName("items")
	val items: List<UserModel>
)



