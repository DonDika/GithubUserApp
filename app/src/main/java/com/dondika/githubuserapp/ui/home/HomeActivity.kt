package com.dondika.githubuserapp.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.databinding.ActivityHomeBinding
import com.dondika.githubuserapp.ui.detail.DetailActivity
import com.dondika.githubuserapp.ui.adapter.UserAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setListener()
        setAdapter()
    }

    private fun setListener() {

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchUsername.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = getString(R.string.username)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(inputUsername: String): Boolean {
                    clearFocus()
                    homeViewModel.searchUser(inputUsername)
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }

    }

    private fun setAdapter() {
        val userAdapter = UserAdapter()
        homeViewModel.listUser.observe(this){
            userAdapter.setListUsers(it)
            //Log.e("followV", "DATA DI SEARCH ACT $it")
        }
        binding.rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        }
        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserItem) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                startActivity(intent)
            }
        })
    }

}