package com.dondika.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.remote.response.DetailResponse
import com.dondika.githubuserapp.databinding.ActivityDetailBinding
import com.dondika.githubuserapp.ui.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setViewPager()
    }

    private fun getData() {
        val userData = intent.getStringExtra(EXTRA_USER)

        detailViewModel.getDetailUser(userData!!)
        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }
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
            setDisplayHomeAsUpEnabled(true)
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.apply {
                progressBar.visibility = View.VISIBLE
                container1.visibility = View.GONE
                container2.visibility = View.GONE
                followTab.visibility = View.GONE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.GONE
                container1.visibility = View.VISIBLE
                container2.visibility = View.VISIBLE
                followTab.visibility = View.VISIBLE
            }
        }
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