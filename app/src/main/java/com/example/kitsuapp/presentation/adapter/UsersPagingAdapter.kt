package com.example.kitsuapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kitsuapp.R
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.ItemUserBinding
import com.example.kitsuapp.model.UserUI

/**
 * Класс [AnimePagingAdapter] является адаптером для RecyclerView, который используется
 * для отображения списка пользователей. Он реализует интерфейс PagingDataAdapter для поддержки
 * функциональности постраничной загрузки данных, и принимает лямбду [onItemCLick] для обработки
 * нажатий на элементы списка.
 */
class UsersPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<UserUI, UsersPagingAdapter.UsersViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(data: UserUI) = with(binding) {
            pbUser.visible()
            data.attributes?.avatar?.original?.let {
                ivUserAvatar.load(it) {
                    placeholder(R.drawable.place_holder)
                    listener { _, _ ->
                        pbUser.gone()
                    }
                }
            }
            data.attributes?.name?.let { tvUserName.text = it }
            data.attributes?.followersCount?.let { tvUserFollowers.text = "$it followers" }
            tvUserName.text = data.attributes!!.name!!
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
