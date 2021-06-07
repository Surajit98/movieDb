package com.sd.moviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sd.moviedb.data.database.dao.MovieDao
import com.sd.moviedb.data.database.dao.RemoteKeysDao
import com.sd.moviedb.model.Movies
import com.sd.moviedb.model.RemoteKeys
import com.sd.moviedb.utils.Converters

@Database(
    entities = [Movies::class,RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    abstract fun getRemoteKeysDao(): RemoteKeysDao

}