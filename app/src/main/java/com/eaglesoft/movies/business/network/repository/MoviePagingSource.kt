package com.eaglesoft.movies.business.network.repository

import android.util.Log
import androidx.paging.PagingSource
import com.eaglesoft.movies.business.cache.database.AppDatabase
import com.eaglesoft.movies.business.cache.mapper.CacheMapper
import com.eaglesoft.movies.business.cache.model.CeMovie
import com.eaglesoft.movies.business.domain.model.Movie
import com.eaglesoft.movies.business.network.mapper.NetworkMapper

class MoviePagingSource(
    private val repository: MovieRepository,
    private val sortBy: String?
) : PagingSource<Int, CeMovie>() {
    private val TAG = "MoviePagingSource"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CeMovie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getMovies(page = nextPage, sortBy = sortBy)

            val domainMovie = NetworkMapper().mapFromEntityList(movieListResponse?.results)

            if (!domainMovie.isNullOrEmpty()) {
                for (movie in domainMovie) {
                    AppDatabase.INSTANCE?.movieDao()?.insert(CacheMapper().mapToEntity(movie))
                }
            }
            val cacheMovieResult = AppDatabase.INSTANCE?.movieDao()?.getPagedList()

            /*val domainMovieResult: MutableList<Movie>? = arrayListOf()
            if (!cacheMovieResult.isNullOrEmpty()) {
                for (movie in cacheMovieResult) {
                    CacheMapper().mapFromEntity(movie)?.let { domainMovieResult?.add(it) }
                }
            }*/


            LoadResult.Page(
                data = cacheMovieResult,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse?.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /*override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WeMovie> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getMovies(page = nextPage, sortBy = sortBy)

            val domainMovie = NetworkMapper().mapFromEntityList(movieListResponse?.results)

            if (!domainMovie.isNullOrEmpty()) {
                for (movie in domainMovie) {
                    AppDatabase.INSTANCE?.movieDao()?.insert(CacheMapper().mapToEntity(movie))
                }
            }
            val cacheMovieResult = AppDatabase.INSTANCE?.movieDao()?.getList()
            Log.e(TAG, "load: " + cacheMovieResult?.size)

            LoadResult.Page(
                data = movieListResponse?.results!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }*/
}