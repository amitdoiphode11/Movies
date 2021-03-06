package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import com.eaglesoft.movies.business.domain.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeMovie(
    var backdrop_path: String? = null,
    var genres: List<Genre>? = null,
    var homepage: String? = null,
    var id: Int? = 0,
    var imdbId: String? = null,
    var original_language: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Float? = 0f,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var voteAverage: Float? = 0f,
    var vote_count: Int? = 0
) : Parcelable