package com.sd.moviedb.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sd.moviedb.constants.AppConstants.FIRST_PAGE
import com.sd.moviedb.constants.AppConstants.LANGUAGE
import com.sd.moviedb.constants.AppConstants.SORT_BY
import com.sd.moviedb.data.network.ApiService
import com.sd.moviedb.data.database.AppDatabase
import com.sd.moviedb.model.Movies
import com.sd.moviedb.model.RemoteKeys
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException


@OptIn(ExperimentalPagingApi::class)
class MoviePagingSource(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, Movies>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movies>): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = apiService.getMovies(page, LANGUAGE, SORT_BY)
            val isEndOfList = response.body()?.totalPages == page
            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRemoteKeysDao().clearRemoteKeys()
                    appDatabase.getMovieDao().clearMovies()
                }
                val prevKey = if (page == FIRST_PAGE) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.body()?.results?.map {
                    RemoteKeys(id = it.title!!, prevKey = prevKey, nextKey = nextKey)
                }
                if (keys != null) {
                    appDatabase.getRemoteKeysDao().insertAll(keys)
                }
                response.body()?.results?.let { appDatabase.getMovieDao().insertAll(it) }
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    /**
     * this returns the page key or the final end of list success result
     */
    @OptIn(ExperimentalPagingApi::class)
    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, Movies>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: FIRST_PAGE
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
               /* val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = false)
                remoteKeys.prevKey*/
                return MediatorResult.Success(endOfPaginationReached = false)
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, Movies>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> appDatabase.getRemoteKeysDao().remoteKeys(movie.title!!) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, Movies>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> appDatabase.getRemoteKeysDao().remoteKeys(movie.title!!) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, Movies>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.title?.let { id ->
                appDatabase.getRemoteKeysDao().remoteKeys(id)
            }
        }
    }


}


