package com.eaglesoft.movies.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eaglesoft.movies.business.network.repository.MovieRepository
import com.eaglesoft.movies.framework.details.MovieDetailsViewModel
import com.eaglesoft.movies.framework.list.MovieListViewModel

class ViewModelFactory(private val repository: MovieRepository?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}