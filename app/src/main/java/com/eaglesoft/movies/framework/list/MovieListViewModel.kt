package com.eaglesoft.movies.framework.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eaglesoft.movies.R
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.repository.MoviePagingSource
import com.eaglesoft.movies.business.network.repository.MovieRepository
import com.eaglesoft.movies.business.util.MoviesFilterType
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: MovieRepository?) : ViewModel() {
    private val sortBy: MutableLiveData<MoviesFilterType> = MutableLiveData<MoviesFilterType>()
    private val currentTitle = MutableLiveData<Int>()

    fun getMoviesList(sortByValue: MoviesFilterType?): Flow<PagingData<WeMovie>> {

        sortBy.value = sortByValue
        currentTitle.value = R.string.action_popular

        return Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(repository!!, sortBy.value?.name)
        }.flow.cachedIn(viewModelScope)
    }

    fun getCurrentSorting(): MoviesFilterType? {
        return sortBy.value
    }

    fun getCurrentTitle(): LiveData<Int?>? {
        return currentTitle
    }

    fun setSortMoviesBy(id: Int) {
        val filterType: MoviesFilterType?
        var title: Int? = null
        when (id) {
            R.id.action_popular_movies -> {
                if (sortBy.value === MoviesFilterType.POPULAR) return
                filterType = MoviesFilterType.POPULAR
                title = R.string.action_popular
            }
            R.id.action_top_rated -> {
                if (sortBy.value === MoviesFilterType.TOP_RATED) return
                filterType = MoviesFilterType.TOP_RATED
                title = R.string.action_top_rated
            }
            R.id.action_now_playing -> {
                if (sortBy.value === MoviesFilterType.NOW_PLAYING) return
                filterType = MoviesFilterType.NOW_PLAYING
                title = R.string.action_now_playing
            }
            else -> throw IllegalArgumentException("unknown sorting id")
        }
        sortBy.value = filterType
        currentTitle.value = title
    }


}