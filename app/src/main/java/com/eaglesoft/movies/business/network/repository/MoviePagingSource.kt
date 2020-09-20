package com.eaglesoft.movies.business.network.repository

import androidx.paging.PagingSource
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.util.MoviesFilterType

class MoviePagingSource(
    private val repository: MovieRepository,
    private val sortBy: String?
) : PagingSource<Int, WeMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WeMovie> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getMovies(page = nextPage, sortBy = sortBy)

            LoadResult.Page(
                data = movieListResponse?.results!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}