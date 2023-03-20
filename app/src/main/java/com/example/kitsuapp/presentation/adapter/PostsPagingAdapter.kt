package com.example.kitsuapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.loadImage
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.ItemPostsBinding
import com.example.kitsuapp.model.PostsDataUI

class PostsPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<PostsDataUI, PostsPagingAdapter.AnimeViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeViewHolder(
        ItemPostsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class AnimeViewHolder(private val binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: PostsDataUI) = with(binding) {
            tvContent.text = data.attributes.content
            tvUsername.text = data.relationships?.userModel?.attributes?.name
            data.relationships?.userModel?.attributes?.avatar?.medium?.let{ivUserAvatar.loadImage(it)}
            if (data.attributes.embed?.url != null) {
                ivContentImage.visible()
                ivContentImage.loadImage(data.attributes.embed.url)
            } else {
                ivContentImage.gone()
            }
            if(data.attributes.spoiler != null) {
                if (data.attributes.spoiler) {
                    tvContent.gone()
                    ivContentImage.gone()
                    spoilerContainer.visible()
                }
            }

            binding.spoilerContainer.setOnClickListener {
                binding.tvContent.visible()
                binding.spoilerContainer.gone()
                if (data.attributes.embed?.url != null) {
                    binding.ivContentImage.visible()
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<PostsDataUI>() {
            override fun areItemsTheSame(oldItem: PostsDataUI, newItem: PostsDataUI): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: PostsDataUI, newItem: PostsDataUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}
