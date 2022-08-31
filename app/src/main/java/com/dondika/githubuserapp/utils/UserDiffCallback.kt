package com.dondika.githubuserapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.dondika.githubuserapp.data.User
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.response.UserItem

class UserDiffCallback(
    private val oldUserList: List<UserItem>,
    private val newUserList: List<UserItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldUserList.size
    }

    override fun getNewListSize(): Int {
        return newUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].username == newUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

}