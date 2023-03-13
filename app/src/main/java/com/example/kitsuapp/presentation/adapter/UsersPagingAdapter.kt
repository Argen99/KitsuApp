package com.example.kitsuapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.User
import com.example.kitsuapp.core.extension.loadImage
import com.example.kitsuapp.databinding.ItemUsersBinding
import com.example.kitsuapp.model.UserUI

class UsersPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<UserUI, UsersPagingAdapter.UsersViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        ItemUsersBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class UsersViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(data: UserUI) = with(binding) {
            data.attributes?.avatar?.original?.let { ivUserAvatar.loadImage(it) }
            data.attributes?.name?.let { tvUserName.text = it }
            data.attributes?.followersCount?.let{ tvUserFollowers.text = "$it followers" }
            tvUserName.text = data.attributes!!.name!!
        }

        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<UserUI>() {
            override fun areItemsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}
