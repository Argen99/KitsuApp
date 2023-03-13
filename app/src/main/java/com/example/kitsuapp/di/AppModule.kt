package com.example.kitsuapp.di

import com.example.kitsuapp.presentation.fragment.anime.AnimeViewModel
import com.example.kitsuapp.presentation.fragment.main.MainViewModel
import com.example.kitsuapp.presentation.fragment.manga.MangaViewModel
import com.example.kitsuapp.presentation.fragment.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel()
    }

    viewModel<AnimeViewModel> {
        AnimeViewModel(getAnimeUseCase = get())
    }

    viewModel<MangaViewModel> {
        MangaViewModel(getMangaUseCase = get())
    }

    viewModel<UsersViewModel> {
        UsersViewModel(getUsersUseCase = get())
    }
}