package com.example.data.di

import com.example.data.local.prefs.TokenManager
import com.example.data.remote.api_service.ApiService
import com.example.data.remote.repository.MainRepositoryImpl
import com.example.domain.repository.MainRepository
import org.koin.dsl.module

val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<ApiService>()
        )
    }

    single<TokenManager> {
        TokenManager(context = get())
    }
}