package com.dondika.githubuserapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dondika.githubuserapp.data.GithubUser
import com.dondika.githubuserapp.databinding.ItemUsersBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUsers = ArrayList<GithubUser>()

    fun setData(data: List<GithubUser>) {
        listUsers.clear()
        listUsers.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }


    inner class ViewHolder(private val binding: ItemUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GithubUser){
            binding.apply {
                tvName.text = user.Name
                tvUsername.text = user.Username
                tvCompany.text = user.Company
                tvRepository.text = user.Repository
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