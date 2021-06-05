package com.sd.moviedb.data.datasource

import com.sd.moviedb.data.ApiService
import com.sd.moviedb.data.dao.MovieDao

class MovieDataSource(val apiService: ApiService) : BaseDataSource() {

    suspend fun getMovies(page:Int) = getResult { apiService.getMovies(page) }
}