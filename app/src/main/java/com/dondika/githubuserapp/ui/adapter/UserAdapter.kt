package com.dondika.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dondika.githubuserapp.data.User
import com.dondika.githubuserapp.data.remote.response.SearchResponse
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.databinding.ItemUsersBinding
import com.dondika.githubuserapp.utils.UserDiffCallback

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUsers = ArrayList<UserItem>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setListUsers(listUsers: List<UserItem>) {
        val diffCallback = UserDiffCallback(this.listUsers, listUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUsers.clear()
        this.listUsers.addAll(listUsers)
        diffResult.dispatchUpdatesTo(this@UserAdapter)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    inner class UserViewHolder(private val binding: ItemUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem){
            binding.apply {
                tvName.text = user.username
                /*
                tvUsername.text = user.Username
                tvCompany.text = user.Company
                imgUser.setImageResource(user.Avatar)*/
                Glide.with(itemView).load(user.avatarUrl).into(imgUser)
                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(user)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: UserItem)
    }

}