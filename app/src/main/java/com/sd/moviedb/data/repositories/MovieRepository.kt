package com.sd.moviedb.data.repositories

import com.sd.moviedb.data.dao.MovieDao
import com.sd.moviedb.data.datasource.MovieDataSource
import com.sd.moviedb.data.getDataFromSource

class MovieRepository(
    private val dataSource: MovieDataSource,
    private val movieDao: MovieDao
) {

    fun getMovies(id: Int) = getDataFromSource(
        databaseQuery = { movieDao.getMovies() },
        networkCall = { dataSource.getMovies(id) },
        saveCallResult = { it.results?.let { it1 -> movieDao.insertAll(it1) } }
    )


}