package com.example.githubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.databinding.UsersListItemBinding
import com.example.githubusers.models.UsersModel
import io.getstream.avatarview.coil.loadImage

class UsersListAdapter(private val usersList: List<UsersModel>) :
    RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    var onEndOfListReached: ((Int) -> Unit)? = null
    var onItemClicked: ((UsersModel) -> Unit)? = null

    class ViewHolder(val binding: UsersListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        if (user.siteAdmin) {
            holder.binding.userNameLl.visibility = View.VISIBLE
            holder.binding.userNameTextview.visibility = View.GONE
            holder.binding.staffNameTextview.text = user.login
        } else {
            holder.binding.userNameLl.visibility = View.GONE
            holder.binding.userNameTextview.visibility = View.VISIBLE
            holder.binding.userNameTextview.text = user.login
        }

        holder.binding.userAvatarView.loadImage(user.avatarURL)

        if (position == usersList.size - 1) {
            onEndOfListReached?.invoke(user.id)
        }

        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(user)
        }
    }
}