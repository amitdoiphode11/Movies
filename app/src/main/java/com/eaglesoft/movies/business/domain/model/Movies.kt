package com.eaglesoft.movies.business.domain.model

import android.os.Parcelable
import com.eaglesoft.movies.business.network.model.WeMovie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    var page: Int? = 0,
    var movies: List<WeMovie>? = null,
    var totalResults: Int = 0,
    var totalPages: Int = 0
) : Parcelable