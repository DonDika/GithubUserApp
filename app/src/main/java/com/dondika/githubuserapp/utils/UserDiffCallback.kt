package com.dondika.githubuserapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.dondika.githubuserapp.data.User

class UserDiffCallback(
    private val oldUserList: List<User>,
    private val newUserList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldUserList.size
    }

    override fun getNewListSize(): Int {
        return newUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].Name == newUserList[newItemPosition].Name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

}