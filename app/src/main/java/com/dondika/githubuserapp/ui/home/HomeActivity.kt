package com.dondika.githubuserapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.data.User
import com.dondika.githubuserapp.databinding.ActivityHomeBinding
import com.dondika.githubuserapp.ui.detail.DetailActivity
import com.dondika.githubuserapp.ui.adapter.UserAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
    }

    private fun setAdapter() {
        val userAdapter = UserAdapter()
       // userAdapter.setListUsers(GithubUserData.listUser(this@HomeActivity))

        binding.rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        }

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user)
                startActivity(intent)
            }

        })
    }

}