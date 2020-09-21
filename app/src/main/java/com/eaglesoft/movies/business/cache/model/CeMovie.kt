package com.eaglesoft.movies.business.cache.model

import android.os.Parcelable
import androidx.room.Entity
import com.eaglesoft.movies.business.domain.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class CeMovie(
    var backdropPath: String? = null,
    var genres: String? = null,
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