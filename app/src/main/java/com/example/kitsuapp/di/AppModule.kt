package com.example.kitsuapp.di

import com.example.kitsuapp.presentation.fragment.main_flow.main.anime.AnimeViewModel
import com.example.kitsuapp.presentation.fragment.main_flow.create_post.CreatePostViewModel
import com.example.kitsuapp.presentation.fragment.sign_flow.login.LoginViewModel
import com.example.kitsuapp.presentation.fragment.main_flow.main.MainViewModel
import com.example.kitsuapp.presentation.fragment.main_flow.main.manga.MangaViewModel
import com.example.kitsuapp.presentation.fragment.main_flow.main.posts.PostsViewModel
import com.example.kitsuapp.presentation.fragment.main_flow.main.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel()
    }

    viewModel<AnimeViewModel> {
        AnimeViewModel(
            getAnimeUseCase = get(),
            getCategoriesUseCase = get()
        )
    }

    viewModel<MangaViewModel> {
        MangaViewModel(
            getMangaUseCase = get(),
            getCategoriesUseCase = get()
        )
    }

    viewModel<UsersViewModel> {
        UsersViewModel(getUsersUseCase = get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(loginUseCase = get())
    }

    viewModel<PostsViewModel> {
        PostsViewModel(
            getPostsUseCase = get(),
            getUserByPostIdUseCase = get()
        )
    }

    viewModel<CreatePostViewModel> {
        CreatePostViewModel(
            getUserByNameUseCase = get(),
            createPostUseCase = get()
        )
    }
}