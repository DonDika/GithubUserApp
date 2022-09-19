package com.dondika.githubuserapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.dondika.githubuserapp.data.model.UserModel

class UserDiffCallback(
    private val oldUserList: List<UserModel>,
    private val newUserList: List<UserModel>
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