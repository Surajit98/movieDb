package com.sd.moviedb.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sd.moviedb.model.Movies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<Movies>)

    @Query("select * from movies")
    fun getMovies(): LiveData<Movies>

    @Update
    fun updateMovies(user: Movies)


}