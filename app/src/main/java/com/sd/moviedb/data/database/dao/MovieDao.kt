package com.sd.moviedb.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.sd.moviedb.model.Movies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<Movies>)

    @Query("select * from movies")
    fun getMovies(): PagingSource<Int, Movies>

    @Query("select * from movies where favourite")
    fun getFavMovies(): LiveData<List<Movies>>

    @Update
    fun update(movies: Movies)

    @Query("delete from movies")
    suspend fun clearMovies()


}