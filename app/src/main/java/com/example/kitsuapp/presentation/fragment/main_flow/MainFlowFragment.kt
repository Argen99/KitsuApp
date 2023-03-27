package com.example.kitsuapp.presentation.fragment.main_flow

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFlowFragment
import com.example.kitsuapp.databinding.FragmentMainFlowBinding
import com.example.kitsuapp.presentation.fragment.main_flow.main.MainFragment

/**
 * [MainFragment] Фрагмент со своим контейнером и графом для навигации в проекте
 * @author Argen
 * @since 1.0v
 */
class MainFlowFragment :
    BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_fragment_main) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}