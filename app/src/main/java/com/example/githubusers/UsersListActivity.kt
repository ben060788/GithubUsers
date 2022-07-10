package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.databinding.ActivityUsersListBinding

class UsersListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUsersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}