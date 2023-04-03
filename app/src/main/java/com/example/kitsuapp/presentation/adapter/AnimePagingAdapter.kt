package com.example.kitsuapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kitsuapp.R
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.ItemAnimeBinding
import com.example.kitsuapp.model.DataUI

/**
 * [AnimePagingAdapter] Класс [AnimePagingAdapter] является адаптером для RecyclerView, который используется
 * для отображения списка аниме. Он реализует интерфейс PagingDataAdapter для поддержки
 * функциональности постраничной загрузки данных, и принимает лямбду [onItemCLick] для обработки
 * нажатий на элементы списка.
 */
class AnimePagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<DataUI, AnimePagingAdapter.AnimeViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeViewHolder(
        ItemAnimeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_rv)
        holder.itemView.startAnimation(anim)

    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: DataUI) = with(binding) {
            progressBar.visible()
            data.attributes?.posterImage?.original?.let {
                ivImage.load(it) {
                    placeholder(R.drawable.place_holder)
                    listener { _, _ ->
                        progressBar.gone()
                    }
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
            }
        }
    }

    /**
     * [diffCallBack] - это объект, реализующий интерфейс DiffUtil.ItemCallback, который используется
     * для определения того, что является новым элементом в списке. Он сравнивает элементы по id и
     * содержанию, чтобы определить, нужно ли обновлять элемент в списке при изменении данных.
     */
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<DataUI>() {
            override fun areItemsTheSame(oldItem: DataUI, newItem: DataUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataUI, newItem: DataUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}

