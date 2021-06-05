package com.sd.moviedb.di

import android.content.Context
import com.sd.moviedb.ui.login.LoginViewModel
import com.sd.moviedb.ui.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainActivityViewModel(get()) }
    //viewModel { (context: Context) -> MainActivityViewModel(context) }
    viewModel(named(MainActivity::class.java.canonicalName as String)) { (id: Context) ->
        MainActivityViewModel(
            id
        )
    }

    viewModel { LoginViewModel(get(),get()) }

    viewModel { RegistrationViewModel(get(),get()) }

}