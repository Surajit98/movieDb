package com.sd.moviedb.callback

import com.sd.moviedb.model.Movies

interface MovieItemClickListener {
    fun toggleLike(movie: Movies)
}