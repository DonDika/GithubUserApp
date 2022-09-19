package com.dondika.githubuserapp.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.databinding.ActivityHomeBinding
import com.dondika.githubuserapp.ui.detail.DetailActivity
import com.dondika.githubuserapp.ui.adapter.UserAdapter
import com.dondika.githubuserapp.ui.favorite.FavoriteActivity
import com.dondika.githubuserapp.ui.setting.SettingActivity
import com.dondika.githubuserapp.utils.Result
import com.dondika.githubuserapp.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.favorite -> {
                val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.setting -> {
                val intent = Intent(this@HomeActivity, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }

    private fun setListener() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchUsername.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(inputUsername: String): Boolean {
                    clearFocus()
                    searchUsername(inputUsername)
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun searchUsername(inputUsername: String) {
        homeViewModel.searchUser(inputUsername).observe(this@HomeActivity){
            when(it){
                is Result.Loading -> onLoading()
                is Result.Success -> { it.data?.let { user -> onSuccess(user) } }
                is Result.Error -> onFailed()
            }
        }
    }

    private fun setAdapter() {
        userAdapter = UserAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserModel) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                startActivity(intent)
            }
        })
    }

    private fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvUsers.visibility = View.GONE
    }

    private fun onSuccess(data: List<UserModel>) {
        binding.progressBar.visibility = View.GONE
        binding.rvUsers.visibility = View.VISIBLE
        userAdapter.setListUsers(data)
    }

    private fun onFailed() {
        binding.apply {
            progressBar.visibility = View.GONE
            rvUsers.visibility = View.GONE
            Toast.makeText(this@HomeActivity, "User Not Found!", Toast.LENGTH_SHORT).show()
        }
    }


}