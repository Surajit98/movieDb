package com.sd.moviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sd.moviedb.data.repositories.MovieRepository
import com.sd.moviedb.model.Movies
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    fun fetchMovies(): Flow<PagingData<Movies>> {
        return repository.getMoviesFlowDb().cachedIn(viewModelScope)
    }

}