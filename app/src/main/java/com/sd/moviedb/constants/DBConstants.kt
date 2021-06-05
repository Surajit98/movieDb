package com.sd.moviedb.constants

class DBConstants {

    companion object {
        const val LOCAL_DB_NAME = "peomify_db"


        const val DATABASE_REFERENCE = "https://the-poet-7f5f6.firebaseio.com"

        //Collections
        const val USERS = "users"
        const val AUTHORS = "authors"
        const val POEMS = "poems"
        const val STATS = "stats"


        //documents
        const val DATA = "data"


        //Fields
        const val TOTAL_COUNT = "total_count"
        const val COUNT = "count"
        const val AUTHOR_COUNT = "author_count"
        const val POEM_COUNT = "poem_count"
        const val DATE_ADDED = "date_added"
        const val AUTHOR_ID = "author_id"
        const val LIKE = "likes"
        const val FAVOURITE = "favourites"
        const val LIKED_POEM = "liked_poem"
        const val FAVOURITE_POEM = "favourite_poem"
        const val LIKED_AUTHOR = "liked_author"
        const val FAVOURITE_AUTHOR = "favourite_author"

        //image path authors
        const val IMAGE_PATH = "$AUTHORS/"


    }
}