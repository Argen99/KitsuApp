package com.example.data.di

import com.example.data.local.prefs.TokenManager
import com.example.data.remote.api_service.*
import com.example.data.remote.repository.*
import com.example.domain.repository.*
import org.koin.dsl.module

/**
 * [dataModule] koin module для зависимостей data модуля
 */
val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<MainApiService>()
        )
    }

    single<AnimeRepository> {
        AnimeRepositoryImpl(
            apiService = get<AnimeApiService>()
        )
    }

    single<MangaRepository> {
        MangaRepositoryImpl(
            apiService = get<MangaApiService>()
        )
    }

    single<PostRepository> {
        PostRepositoryImpl(
            apiService = get<PostApiService>()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            apiService = get<UserApiService>()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            apiService = get<AuthApiService>()
        )
    }

    single<TokenManager> {
        TokenManager(context = get())
    }
}