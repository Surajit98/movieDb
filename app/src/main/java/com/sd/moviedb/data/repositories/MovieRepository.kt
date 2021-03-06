package com.sd.moviedb.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sd.moviedb.constants.AppConstants.DEFAULT_PAGE_SIZE
import com.sd.moviedb.data.database.AppDatabase
import com.sd.moviedb.data.network.ApiService
import com.sd.moviedb.data.paging.MovieSourceMediator
import com.sd.moviedb.model.Movies
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE * 2,
            enablePlaceholders = false
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getMoviesFlowDb(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Movies>> {

        val pagingSourceFactory = { appDatabase.getMovieDao().getMovies() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MovieSourceMediator(apiService, appDatabase)
        ).flow
    }

    fun updateMovie(movies: Movies) {
        movies.favourite = !movies.favourite
        appDatabase.getMovieDao().update(movies)
    }

    fun getAllFav()=appDatabase.getMovieDao().getFavMovies()


}