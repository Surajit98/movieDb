package com.sd.moviedb.di

import android.content.Context
import androidx.room.Room
import com.sd.moviedb.constants.DatabaseConstants
import com.sd.moviedb.data.database.dao.MovieDao
import com.sd.moviedb.data.database.dao.RemoteKeysDao
import com.sd.moviedb.data.database.AppDatabase
import org.koin.dsl.module

var dataBaseModule = module {


    fun provideDatabase(context: Context): AppDatabase? {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, DatabaseConstants.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.getMovieDao()
    }

    fun provideKeysDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.getRemoteKeysDao()
    }


    single { provideDatabase(get()) }
    single { provideMovieDao(get()) }
    single { provideKeysDao(get()) }


}