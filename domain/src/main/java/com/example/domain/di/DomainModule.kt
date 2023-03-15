package com.example.domain.di

import com.example.domain.repository.MainRepository
import com.example.domain.use_cases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetAnimeUseCase> {
        GetAnimeUseCase(repository = get<MainRepository>())
    }

    factory<GetMangaUseCase> {
        GetMangaUseCase(repository = get<MainRepository>())
    }

    factory<GetUsersUseCase> {
        GetUsersUseCase(repository = get<MainRepository>())
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCase(repository = get<MainRepository>())
    }

    factory<LoginUseCase> {
        LoginUseCase(repository = get<MainRepository>())
    }
}