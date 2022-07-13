package com.example.githubusers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.ActivityUsersListBinding
import com.example.githubusers.models.UsersListResponseModel
import com.example.githubusers.models.UsersModel

class UsersListActivity : AppCompatActivity(), UsersListContract.View {
    lateinit var binding: ActivityUsersListBinding
    var presenter: UsersListPresenter? = null
    val users = mutableListOf<UsersModel>()
    val adapter = UsersListAdapter(users)
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        binding.indexRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.indexRecyclerView.adapter = adapter

        presenter = UsersListPresenter(this, UsersListResponseModel())
        lifecycleScope.launchWhenCreated {
            presenter!!.onLoadData()
        }

        binding.indexRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    readNextPage(users[adapter.itemCount - 1].id)
                }
            }
        })

        adapter.onItemClicked = { it ->
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra("login", it.login)
            startActivity(intent)
        }
    }

    override fun showProgress() {
        binding.userListProgressBar.visibility = View.VISIBLE
        binding.indexRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        binding.userListProgressBar.visibility = View.GONE
        binding.indexRecyclerView.visibility = View.VISIBLE
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun readNextPage(id: Int) {
        presenter!!.onLoadNextPage(id)
    }

    override fun setUsersList(usersList: List<UsersModel>) {
        users.addAll(usersList)
        adapter.notifyDataSetChanged()
    }
}