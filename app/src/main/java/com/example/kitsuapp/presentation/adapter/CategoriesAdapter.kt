package com.example.kitsuapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kitsuapp.databinding.ItemCategoriesBinding
import com.example.kitsuapp.model.CategoriesDataUI

/**
 * [CategoriesAdapter] используется для отображения списка категорий
 * в виде чекбоксов. Адаптер принимает список категорий типа CategoriesDataUI, где каждая категория
 * представлена атрибутами (attributes) в объекте класса CategoriesDataUI. Каждый элемент списка
 * отображается в виде текста с чекбоксом, который может быть выделен или снят. Класс также
 * содержит методы для получения выбранных элементов [getSelectedItems], очистки выбранных
 * элементов [clearSelectedItems] и обновления данных [submitData].
 */
class CategoriesAdapter(
    private var list: List<CategoriesDataUI>
) : Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val selectedItems = arrayListOf<String>()

    fun getSelectedItems(): ArrayList<String> {
        return selectedItems
    }

    fun clearSelectedItems() {
        selectedItems.clear()
        list.forEach {
            it.attributes.isChecked = false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<CategoriesDataUI>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = list.size

    inner class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val data = list[position]
            binding.checkboxRv.text = data.attributes.title
            binding.checkboxRv.setOnCheckedChangeListener(null)
            binding.checkboxRv.isChecked = data.attributes.isChecked
            binding.checkboxRv.setOnCheckedChangeListener { _, isChecked ->
                data.attributes.isChecked = isChecked
                if (data.attributes.isChecked) {
                    selectedItems.add(data.attributes.title)
                } else {
                    selectedItems.remove(data.attributes.title)
                }
            }
        }
    }
}