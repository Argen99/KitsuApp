package com.example.kitsuapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kitsuapp.R
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.PagerBoardBinding
import com.example.kitsuapp.model.OnBoardUI

/**
 * Адаптер [OnBoardAdapter] используется для управления отображением данных в ViewPager
 * внутри BoardFragment, отображая пользователю информацию на страницах OnBoarding.
 */
class OnBoardAdapter(private val context: Context) : Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    /**
     * Адаптер содержит список [list] OnBoardUI, каждый элемент которого содержит заголовок,
     * описание и изображение. Каждый элемент списка отображается на своей странице ViewPager.
     * Когда пользователь листает страницы ViewPager, внутренний слушатель OnPageChangeCallback
     * изменяет видимость кнопок в зависимости от текущей страницы. Кнопка "Next" скрывается
     * на последней странице, а кнопка "Start" становится видимой только на последней странице.
     */
    private val list = listOf(
        OnBoardUI(
            context.getString(R.string.on_board_title_main),
            context.getString(R.string.on_board_desc_main),
            R.drawable.on_board_1
        ),
        OnBoardUI(
            context.getString(R.string.on_board_title_details),
            context.getString(R.string.on_board_desc_details),
            R.drawable.on_board_2
        ),
        OnBoardUI(
            context.getString(R.string.on_board_title_posts),
            context.getString(R.string.on_board_desc_posts),
            R.drawable.on_board_3
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnBoardViewHolder(
        PagerBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    inner class OnBoardViewHolder(private val binding: PagerBoardBinding) :
        ViewHolder(binding.root) {

        fun onBind(model: OnBoardUI, position: Int) = with(binding) {
            image.setImageResource(model.image)
            if (position == 0) {
                pagerContainer.setBackgroundColor(context.getColor(R.color.black))
            }
            if (position == 1) {
                lottie2.visible()
            }
            tvTitle.text = model.title
            tvDesc.text = model.description
        }
    }
}