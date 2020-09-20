package com.eaglesoft.movies.business.network.repository

import com.eaglesoft.movies.business.network.model.WeConfiguration
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.model.WeMovies
import retrofit2.Call


interface MovieRepository {

    suspend fun getMovies(
        page: Int? = 1,
        sortBy: String? = null
    ): WeMovies?

    suspend fun getConfiguration(): Call<WeConfiguration?>?

    suspend fun getMovie(id: Int?): WeMovie?
}