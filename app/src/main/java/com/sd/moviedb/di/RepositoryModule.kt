package com.sd.moviedb.di

import com.sd.moviedb.data.network.ApiService
import com.sd.moviedb.data.database.AppDatabase
import com.sd.moviedb.data.repositories.MovieRepository
import org.koin.dsl.module

var repositoryModule = module {


    // provideMovieDataSource(api: ApiService) = MovieDataSource(api)

    //fun provideMoviePagingDataSource(api: ApiService,appDatabase: AppDatabase) = MoviePagingSource(api,appDatabase)

    fun provideMovieRepository(apiService: ApiService, appDatabase: AppDatabase) =
        MovieRepository(apiService, appDatabase)

   // single { provideMovieDataSource(get()) }
   // single { provideMoviePagingDataSource(get(),get()) }
    single { provideMovieRepository(get(), get()) }
}