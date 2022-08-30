package com.dondika.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dondika.githubuserapp.data.User
import com.dondika.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    private fun setData() {
        val userData = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.apply {
            imgUser.setImageResource(userData.Avatar)
            tvRepository.text = userData.Repository
            tvFollowers.text = userData.Follower
            tvFollowing.text = userData.Following
            tvName.text = userData.Name
            tvCompany.text = userData.Company
            tvLocation.text = userData.Location
        }

        supportActionBar?.apply {
            title = userData.Username
        }

    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}