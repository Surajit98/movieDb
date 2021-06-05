package com.sd.moviedb.data

import com.sd.moviedb.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/discover/movie")
    suspend fun getMovies(page: Int): Response<MovieListResponse>
}