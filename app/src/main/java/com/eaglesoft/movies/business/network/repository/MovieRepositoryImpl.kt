package com.eaglesoft.movies.business.network.repository

import com.eaglesoft.movies.business.network.model.WeConfiguration
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.model.WeMovies
import com.eaglesoft.movies.business.network.services.ApiService
import retrofit2.Call

class MovieRepositoryImpl(private val apiService: ApiService?) : MovieRepository {

    override suspend fun getMovies(page: Int?, sortBy: String?): WeMovies? {
        return apiService?.getMovies(page = page, sortBy = sortBy)
    }

    override suspend fun getConfiguration(): Call<WeConfiguration?>? {
        return apiService?.getConfiguration()
    }

    override suspend fun getMovie(id: Int?): WeMovie? {
        return apiService?.getMovie(id = id)
    }

}