package com.dondika.githubuserapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dondika.githubuserapp.databinding.ActivitySplashBinding
import com.dondika.githubuserapp.ui.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setSplash()
    }

    private fun setSplash() {
        val handler = Handler(mainLooper)
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        },2000L)
    }

}