package com.example.domain.di

import com.example.domain.repository.*
import com.example.domain.use_cases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetAnimeUseCase> {
        GetAnimeUseCase(repository = get<AnimeRepository>())
    }

    factory<GetMangaUseCase> {
        GetMangaUseCase(repository = get<MangaRepository>())
    }

    factory<GetUsersUseCase> {
        GetUsersUseCase(repository = get<UserRepository>())
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCase(repository = get<MainRepository>())
    }

    factory<LoginUseCase> {
        LoginUseCase(repository = get<AuthRepository>())
    }

    factory<GetPostsUseCase> {
        GetPostsUseCase(repository = get<PostRepository>())
    }

    factory<GetUserByPostIdUseCase> {
        GetUserByPostIdUseCase(repository = get<UserRepository>())
    }

    factory<GetUserByNameUseCase> {
        GetUserByNameUseCase(repository = get<UserRepository>())
    }

    factory<CreatePostUseCase> {
        CreatePostUseCase(repository = get<PostRepository>())
    }
}