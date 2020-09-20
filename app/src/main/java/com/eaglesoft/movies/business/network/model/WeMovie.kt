package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeMovie(
    var adult: Boolean = false,
    var backdrop_path: String? = null,
    var budget: Int = 0,
    var genres: List<WeGenre>? = null,
    var homepage: String? = null,
    var id: Int = 0,
    var imdbId: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Float = 0f,
    var poster_path: String? = null,
    var release_date: String? = null,
    var revenue: Int = 0,
    var runtime: Int = 0,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean = false,
    var voteAverage: Float = 0f,
    var vote_count: Int = 0
) : Parcelable