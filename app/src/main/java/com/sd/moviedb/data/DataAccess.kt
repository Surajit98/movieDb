package com.sd.moviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> getDataFromSource(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> ApiResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ApiResult<T>> =
    liveData(Dispatchers.IO) {
        emit(ApiResult.loading())
        val source = databaseQuery.invoke().map { ApiResult.success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ApiResult.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == ApiResult.Status.ERROR) {
            emit(ApiResult.error(responseStatus.message!!))
            emitSource(source)
        }
    }