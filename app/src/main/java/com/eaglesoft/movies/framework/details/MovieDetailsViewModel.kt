package com.eaglesoft.movies.framework.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eaglesoft.movies.business.domain.state.DataState
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieRepository?) : ViewModel() {
    private val TAG = "MovieDetailsViewModel"
    private val _movieDetails = MutableLiveData<DataState<WeMovie?>?>()
    val movieDetails: LiveData<DataState<WeMovie?>?> = _movieDetails

    fun getMoviesDetails(id: Int?) {
        viewModelScope.launch {
            try {
                _movieDetails.postValue(DataState.Loading)
                val result = repository?.getMovie(id)
                if (result != null)
                    _movieDetails.postValue(DataState.Success(result))

            } catch (e: Exception) {
                Log.e(TAG, "getMoviesDetails: ", e)
                _movieDetails.postValue(DataState.Error(e))
            }
        }
    }
    /*fun getMoviesDetails(id: Int?): Flow<DataState<WeMovie?>> = flow {
        viewModelScope.launch {
            try {
                emit(DataState.Loading)
                emit(DataState.Success(repository?.getMovie(id)))
            } catch (e: Exception) {
                Log.e(TAG, "getMoviesDetails: ", e)
                emit(DataState.Error(e))
            }
        }
    }
*/
}