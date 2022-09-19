package com.dondika.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.databinding.ActivityDetailBinding
import com.dondika.githubuserapp.ui.adapter.SectionPagerAdapter
import com.dondika.githubuserapp.utils.Result
import com.dondika.githubuserapp.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setViewPager()
    }

    private fun getData() {
        val username = intent.getStringExtra(EXTRA_USER) as String

        supportActionBar?.apply {
            title = username
            setDisplayHomeAsUpEnabled(true)
        }

        CoroutineScope(Dispatchers.Main).launch {
            detailViewModel.getDetailUser(username).observe(this@DetailActivity){ detailUser ->
                when(detailUser){
                    is Result.Loading ->
                        onLoading()
                    is Result.Success -> {
                        val data = detailUser.data
                        onSuccess(data)
                    }
                    is Result.Error -> {
                        onFailed()
                    }
                }
            }
        }
    }


    private fun onLoading() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun onFailed() {
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    private fun onSuccess(userData: UserModel?) {
        binding.apply {
            progressBar.visibility = View.GONE

            tvRepository.text = userData?.publicRepos.toString()
            tvFollowers.text = userData?.followers.toString()
            tvFollowing.text = userData?.following.toString()
            tvName.text = userData?.name
            tvCompany.text = userData?.company
            tvLocation.text = userData?.location
            Glide.with(this@DetailActivity)
                .load(userData?.avatarUrl)
                .into(imgUser)

            fabFav.visibility = View.VISIBLE

            if (userData?.isFavorites == true) {
                isFavoritesUser(true)
            } else {
                isFavoritesUser(false)
            }

            fabFav.setOnClickListener {
                if (userData?.isFavorites == true){
                    userData.isFavorites = false
                    detailViewModel.deleteFromFavorites(userData)
                    isFavoritesUser(false)
                    Toast.makeText(this@DetailActivity, "User deleted from favorites!", Toast.LENGTH_SHORT).show()
                } else {
                    userData?.isFavorites = true
                    userData?.let { it1 -> detailViewModel.saveAsFavorites(it1) }
                    isFavoritesUser(true)
                    Toast.makeText(this@DetailActivity, "User added to favorites!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isFavoritesUser(isFavorites: Boolean) {
        if (isFavorites) {
            binding.fabFav.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.fabFav.setImageResource(R.drawable.ic_favorite_border)
        }
    }


    private fun setViewPager() {
        val sectionPagerAdapter = SectionPagerAdapter(this@DetailActivity)
        val viewPager: ViewPager2 = binding.followViewPager
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.followTab
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        @StringRes
        private val TAB_TITTLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

}