package com.sd.moviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sd.moviedb.data.dao.MovieDao
import com.sd.moviedb.model.Movies
import com.sd.moviedb.utils.Converters

@Database(
    entities = [Movies::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): MovieDao

}