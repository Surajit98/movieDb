package com.sd.moviedb.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun listToJson(value: ArrayList<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String?): ArrayList<String>? =  if(value==null || value == "null" ||value.isNullOrEmpty()) null else
        ArrayList(listOf(*Gson().fromJson(value, Array<String>::class.java)))
}
