package com.dondika.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dondika.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}