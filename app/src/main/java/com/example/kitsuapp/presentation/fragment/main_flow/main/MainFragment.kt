package com.example.kitsuapp.presentation.fragment.main_flow.main

import androidx.activity.OnBackPressedCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.databinding.FragmentMainBinding
import com.example.kitsuapp.presentation.adapter.FragmentPagerAdapter
import com.example.kitsuapp.presentation.fragment.main_flow.main.anime.AnimeFragment
import com.example.kitsuapp.presentation.fragment.main_flow.main.manga.MangaFragment
import com.example.kitsuapp.presentation.fragment.main_flow.main.posts.PostsFragment
import com.example.kitsuapp.presentation.fragment.main_flow.main.users.UsersFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [MainFragment] Класс MainFragment является подклассом [BaseFragment] и отвечает за управление
 * главным экраном приложения. Он показывает ViewPager с четырьмя разными вкладками,
 * каждая из которых содержит свой фрагмент.
 * @author Argen
 * @since 1.0v
 */
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {
    /** Экземпляр binding для макета этого фрагмента. */
    override val binding by viewBinding(FragmentMainBinding::bind)

    /** Экземпляр view model для этого фрагмента. */
    override val viewModel by viewModel<MainViewModel>()


    /**
     * [initialize] Инициализирует представления фрагмента
     */
    override fun initialize() {
        val pagerAdapter = FragmentPagerAdapter(requireActivity())

        addFragments(pagerAdapter)

        binding.fragmentPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.fragmentPager) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()
        /**
         *  // Добавляет обратный вызов для события нажатия кнопки "назад", завершающий активность при нажатии.
         */
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    /**
     * [addFragments] Добавляет фрагменты и их заголовки в ViewPager.
     */
    private fun addFragments(pagerAdapter: FragmentPagerAdapter) {
        pagerAdapter.addFragment(
            AnimeFragment(),
            getString(R.string.anime)
        )
        pagerAdapter.addFragment(
            MangaFragment(),
            getString(R.string.manga)
        )
        pagerAdapter.addFragment(
            UsersFragment(),
            getString(R.string.users)
        )

        pagerAdapter.addFragment(
            PostsFragment(),
            getString(R.string.posts)
        )
    }
}
