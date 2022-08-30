package com.dondika.githubuserapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dondika.githubuserapp.data.User
import com.dondika.githubuserapp.databinding.ItemUsersBinding
import com.dondika.githubuserapp.utils.UserDiffCallback

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<User>()

    fun setData(listUser: List<User>) {
        val diffCallback = UserDiffCallback(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this@UserAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


    inner class UserViewHolder(private val binding: ItemUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.apply {
                tvName.text = user.Name
                tvUsername.text = user.Username
                tvCompany.text = user.Company
                imgUser.setImageResource(user.Avatar)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user)
                itemView.context.startActivity(intent)
            }
        }
    }

}