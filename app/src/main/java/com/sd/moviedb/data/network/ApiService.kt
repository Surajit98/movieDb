package com.sd.moviedb.data.network

import com.sd.moviedb.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("language") lng: String,
        @Query("sort_by") sort: String
    ): Response<MovieListResponse>

}