package com.sd.moviedb.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sd.moviedb.data.repositories.MovieRepository
import com.sd.moviedb.model.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovies()=repository.getAllFav()

    fun update(movie: Movies){
        viewModelScope.launch(Dispatchers.IO) { repository.updateMovie(movie) }
    }
}