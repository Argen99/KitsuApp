package com.example.kitsuapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kitsuapp.databinding.ItemLoaderBinding

class DefaultLoadStateAdapter(
) : LoadStateAdapter<DefaultLoadStateAdapter.CharacterLoadStateHolder>() {

    override fun onBindViewHolder(holder: CharacterLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharacterLoadStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoaderBinding.inflate(inflater, parent, false)
        return CharacterLoadStateHolder(binding)
    }

    inner class CharacterLoadStateHolder(
        private val binding: ItemLoaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}