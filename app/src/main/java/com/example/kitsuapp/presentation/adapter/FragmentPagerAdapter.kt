package com.example.kitsuapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * [FragmentPagerAdapter] Класс FragmentPagerAdapter представляет собой адаптер для
 * управления списком фрагментов внутри ViewPager. Он расширяет FragmentStateAdapter
 * и предоставляет дополнительные методы для добавления фрагментов и получения заголовков вкладок.
 */
class FragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    /**
     * Приватные переменные [fragmentList] и [fragmentTitleList] используются для хранения списка
     * фрагментов и соответствующих заголовков.
     */
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()

    /**
     * Метод [getTabTitle] возвращает заголовок вкладки для указанной позиции.
     */
    fun getTabTitle(position: Int): String {
        return fragmentTitleList[position]
    }

    /**
     * Метод addFragment(fragment: Fragment, title: String) добавляет фрагмент и
      * соответствующий заголовок в списки.
     */
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    /**
     * Метод [getItemCount] возвращает количество фрагментов в списке.
     */
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    /**
     * Метод [createFragment] создает и возвращает фрагмент для указанной позиции в списке.
     */
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
