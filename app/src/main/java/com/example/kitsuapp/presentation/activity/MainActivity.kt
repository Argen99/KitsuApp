package com.example.kitsuapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.data.local.prefs.TokenManager
import com.example.kitsuapp.R
import com.example.kitsuapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val tokenManager: TokenManager by inject()
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        when {
//            tokenManager.accessToken == null -> {
                navGraph.setStartDestination(R.id.signFlowFragment)
//            }
//            tokenManager.accessToken != null -> {
//                navGraph.setStartDestination(R.id.mainFlowFragment)
//            }
//        }
        navController.graph = navGraph
    }
}