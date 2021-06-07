package com.sd.moviedb.di

import com.sd.moviedb.ui.favourites.FavouritesViewModel
import com.sd.moviedb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { FavouritesViewModel() }

}