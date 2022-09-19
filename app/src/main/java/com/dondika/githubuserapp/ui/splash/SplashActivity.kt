package com.dondika.githubuserapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.dondika.githubuserapp.databinding.ActivitySplashBinding
import com.dondika.githubuserapp.ui.home.HomeActivity
import com.dondika.githubuserapp.ui.setting.SettingViewModel
import com.dondika.githubuserapp.utils.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val settingViewModel: SettingViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

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
            settingViewModel.getThemeSetting().observe(this@SplashActivity){ isDarkModeActive ->
                if (isDarkModeActive) {
                    toHomeActivity()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    toHomeActivity()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        },2000L)
    }

    private fun toHomeActivity() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }

}