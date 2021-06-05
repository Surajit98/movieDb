package com.sd.moviedb.di

import android.content.Context
import androidx.room.Room
import com.sd.moviedb.constants.DatabaseConstants
import com.sd.moviedb.data.dao.MovieDao
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

    fun provideUserDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.getUserDao()
    }

    single { provideDatabase(get()) }
    single { provideUserDao(get()) }


}