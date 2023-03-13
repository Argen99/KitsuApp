package com.example.data.remote

import com.example.data.remote.api_service.ApiService
import com.example.data.remote.repository.MainRepositoryImpl
import com.example.domain.repository.MainRepository
import org.koin.dsl.module

val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<ApiService>())
    }
}