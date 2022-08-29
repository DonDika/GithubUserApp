package com.dondika.githubuserapp.data

import android.content.Context
import android.content.res.TypedArray
import com.dondika.githubuserapp.R

object GithubUserData {

    private lateinit var dataAvatar: TypedArray
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataLocation: Array<String>

    private fun getData(context: Context) {
        dataAvatar = context.resources.obtainTypedArray(R.array.avatar)
        dataName = context.resources.getStringArray(R.array.name)
        dataUsername = context.resources.getStringArray(R.array.username)
        dataCompany = context.resources.getStringArray(R.array.company)
        dataRepository = context.resources.getStringArray(R.array.repository)
        dataFollower = context.resources.getStringArray(R.array.followers)
        dataFollowing = context.resources.getStringArray(R.array.following)
        dataLocation = context.resources.getStringArray(R.array.location)
    }

    fun listUser(context: Context): ArrayList<GithubUser> {
        val listUser = ArrayList<GithubUser>()
        getData(context)
        for (position in dataName.indices) {
            val gitUser = GithubUser(
                dataAvatar.getResourceId(position, -1),
                dataName[position],
                dataUsername[position],
                dataCompany[position],
                dataRepository[position],
                dataFollower[position],
                dataFollowing[position],
                dataLocation[position]
            )
            listUser.add(gitUser)
        }
        return listUser
    }


}