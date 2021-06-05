package com.sd.moviedb.di

import com.sd.moviedb.data.ApiService
import com.sd.moviedb.data.dao.MovieDao
import com.sd.moviedb.data.datasource.MovieDataSource
import com.sd.moviedb.data.repositories.MovieRepository
import org.koin.dsl.module

var repositoryModule = module {


    fun provideMovieDataSource(api: ApiService) = MovieDataSource(api)

    fun provideMovieRepository(movieDataSource: MovieDataSource, movieDao: MovieDao) =
        MovieRepository(movieDataSource, movieDao)

    single { provideMovieDataSource(get()) }
    single { provideMovieRepository(get(), get()) }
}