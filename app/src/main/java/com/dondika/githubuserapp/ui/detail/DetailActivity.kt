package com.dondika.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dondika.githubuserapp.data.remote.response.DetailResponse
import com.dondika.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        val userData = intent.getStringExtra(EXTRA_USER)

        //viewmodel -> fun(userData)
        detailViewModel.getDetailUser(userData!!)

        //observe variable
        detailViewModel.userData.observe(this){
            setData(it)
        }

    }

    private fun setData(userData: DetailResponse) {
        binding.apply {
            tvRepository.text = userData.publicRepos.toString()
            tvFollowers.text = userData.followers.toString()
            tvFollowing.text = userData.following.toString()
            tvName.text = userData.name
            tvCompany.text = userData.company
            tvLocation.text = userData.location
            Glide.with(this@DetailActivity)
                .load(userData.avatarUrl)
                .into(imgUser)
        }

        supportActionBar?.apply {
            title = userData.username
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}