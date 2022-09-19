package com.dondika.githubuserapp.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.databinding.ActivityFavoriteBinding
import com.dondika.githubuserapp.ui.adapter.UserAdapter
import com.dondika.githubuserapp.ui.detail.DetailActivity
import com.dondika.githubuserapp.utils.Result
import com.dondika.githubuserapp.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var userAdapter: UserAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
            title = getString(R.string.fav)
        }

        setupAdapter()
        observerData()
    }

    override fun onResume() {
        super.onResume()
        observerData()
    }

    private fun setupAdapter() {
        userAdapter = UserAdapter()
        binding.rvFavorite.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavoriteActivity, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }
        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: UserModel) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                startActivity(intent)
            }
        })
    }

    private fun observerData() {
        CoroutineScope(Dispatchers.Main).launch {
            favoriteViewModel.getAllFavoritesUser().observe(this@FavoriteActivity){
                when(it){
                    is Result.Loading -> onLoading()
                    is Result.Success -> it.data?.let { it1 ->
                        onSuccess(it1)
                    }
                    is Result.Error -> onFailed()
                }
            }
        }
    }

    private fun onLoading(){
        binding.apply {
            progressBar.visibility = View.VISIBLE
            rvFavorite.visibility = View.GONE
            tvFavoriteError.visibility = View.GONE
        }
    }

    private fun onSuccess(userData: List<UserModel>) {
        userAdapter.setListUsers(userData)
        binding.apply {
            rvFavorite.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            tvFavoriteError.visibility = View.GONE
        }
    }

    private fun onFailed(){
        binding.apply {
            progressBar.visibility = View.GONE
            rvFavorite.visibility = View.GONE
            tvFavoriteError.visibility = View.VISIBLE
            tvFavoriteError.text = getString(R.string.no_fav)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}