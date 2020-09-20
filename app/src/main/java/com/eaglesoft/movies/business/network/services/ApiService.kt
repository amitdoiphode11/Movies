package com.eaglesoft.movies.business.network.services

import com.eaglesoft.movies.business.network.model.WeConfiguration
import com.eaglesoft.movies.business.network.model.WeMovie
import com.eaglesoft.movies.business.network.model.WeMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("primary_release_date.lte") releaseDate: String? = "2020-09-20",
        //@Query("sort_by") sortBy: String? = "release_date.desc",
        @Query("sort_by") sortBy: String?,
        @Query("page") page: Int? = 1
    ): WeMovies?


    @Headers("Cache-Control: public, max-stale=2419200") // 4 weeks
    @GET("/3/configuration")
    suspend fun getConfiguration(): Call<WeConfiguration?>?

    @GET("/3/movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int?,
    ): WeMovie?

}