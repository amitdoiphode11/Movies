package com.eaglesoft.movies.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var backdropPath: String? = null,
    var genres: List<Genre>? = null,
    var homepage: String? = null,
    var id: Int? = 0,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Float? = 0f,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var voteAverage: Float? = 0f,
    var voteCount: Int? = 0
) : Parcelable