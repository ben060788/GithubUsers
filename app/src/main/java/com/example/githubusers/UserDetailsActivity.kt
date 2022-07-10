package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.example.githubusers.databinding.ActivityUserDetailsBinding
import io.getstream.avatarview.coil.loadImage

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userDetailAvatarView.loadImage("https://avatars.githubusercontent.com/u/2?v=4")

    }
}