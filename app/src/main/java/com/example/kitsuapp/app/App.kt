package com.example.kitsuapp.app

import android.app.Application
import com.example.data.remote.dataModule
import com.example.kitsuapp.di.appModule
import com.example.kitsuapp.di.domainModule
import com.example.kitsuapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, dataModule, domainModule))
        }
    }
}