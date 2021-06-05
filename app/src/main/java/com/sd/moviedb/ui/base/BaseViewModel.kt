package com.sd.moviedb.ui.base

import androidx.lifecycle.ViewModel
import com.sd.moviedb.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    val onBackPressed = SingleLiveEvent<Void>()

    fun back() {
        onBackPressed.call()
    }
}