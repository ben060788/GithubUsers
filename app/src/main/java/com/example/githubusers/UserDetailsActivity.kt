package com.example.githubusers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.githubusers.databinding.ActivityUserDetailsBinding
import com.example.githubusers.models.UserDetailsModel
import com.example.githubusers.models.UserDetailsResponseModel
import io.getstream.avatarview.coil.loadImage

class UserDetailsActivity() : AppCompatActivity(), UserDetailsContract.View {
    lateinit var binding: ActivityUserDetailsBinding
    var presenter: UserDetailsPresenter? = null
    var context: Context? = null
    lateinit var login: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login = this.intent.getStringExtra("login") ?: ""
        Log.i("_userDetailsActivity", login.toString())
        binding.userDetailCloseImageview.setOnClickListener {
            this.finish()
        }
        presenter = UserDetailsPresenter(this, UserDetailsResponseModel(login))
        lifecycleScope.launchWhenCreated {
            presenter!!.onLoadData()
        }
    }

    override fun showProgress() {
        binding.userDetailsProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.root.visibility = View.VISIBLE
        binding.userDetailsProgressBar.visibility = View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun setUserDetails(userDetail: UserDetailsModel) {
        binding.userDetailAvatarView.loadImage(userDetail.avatarURL)
        binding.userDetailNameTextview.text = userDetail.userName
        if (userDetail.siteAdmin) {
            binding.userDetailLoginAdminTextview.text = userDetail.loginName
            binding.userDetailLoginAdminTextview.visibility = View.VISIBLE
            binding.userDetailLoginLabelTextview.visibility = View.VISIBLE
            binding.userDetailLoginUserTextview.visibility = View.GONE
        } else {
            binding.userDetailLoginUserTextview.text = userDetail.loginName
            binding.userDetailLoginAdminTextview.visibility = View.GONE
            binding.userDetailLoginLabelTextview.visibility = View.GONE
            binding.userDetailLoginUserTextview.visibility = View.VISIBLE
        }
        binding.userDetailBioTextview.text = userDetail.bio
        binding.userDetailBlogLinkTextview.text = userDetail.blog
        binding.userDetailLocationTextview.text = userDetail.location
    }
}