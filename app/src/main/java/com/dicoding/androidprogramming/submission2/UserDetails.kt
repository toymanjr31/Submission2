package com.dicoding.androidprogramming.submission2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.androidprogramming.submission2.databinding.ActivityUserDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetails : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra<User>(EXTRA_DATA) as User
        binding.tvNameDetail.text = user.name
        binding.tvUsernameDetail.text = user.username
        binding.tvCompanyDetail.text = user.company
        binding.tvLocationDetail.text = user.location
        binding.tvRepositoryDetail.text = user.repository
        Glide.with(this).load(user.avatar).into(binding.imgAvatarDetail)

        binding.btnShare.setOnClickListener {
            val message = binding.tvUsernameDetail.text
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to: "))
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
    }

}