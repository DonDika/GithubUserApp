package com.dondika.githubuserapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    var Avatar: Int,
    var Name: String,
    var Username: String,
    var Company: String,
    var Repository: String,
    var Follower: String,
    var Following: String,
    var Location: String
): Parcelable