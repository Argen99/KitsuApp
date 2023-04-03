package com.example.kitsuapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.data.local.prefs.TokenManager
import com.example.kitsuapp.R
import com.example.kitsuapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

/**
 * [MainActivity] MainActivity
 * @author Argen
 * @since 1.0v
 */
class MainActivity : AppCompatActivity() {

    private val tokenManager: TokenManager by inject()
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    /**
     *[setupNavigation] Устанавливает начальную точку навигации в зависимости от наличия токена доступа.
     *Если токен доступа отсутствует, значит пользователь не авторизован устанавливает стартовый фрагмент [SignFlowFragment].
     *Если токен доступа существует, значит пользователь авторизован устанавливает стартовый фрагмент как [MainFlowFragment].
     */
    private fun setupNavigation() {
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        when {
            tokenManager.accessToken == null -> {
                navGraph.setStartDestination(R.id.signFlowFragment)
            }
            tokenManager.accessToken != null -> {
                navGraph.setStartDestination(R.id.mainFlowFragment)
            }
        }
        navController.graph = navGraph
    }
}