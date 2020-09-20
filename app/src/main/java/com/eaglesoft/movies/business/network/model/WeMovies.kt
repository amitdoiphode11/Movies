package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeMovies(
    var page: Int? = 0,
    var results: List<WeMovie>? = null,
    var totalResults: Int = 0,
    var totalPages: Int = 0
) : Parcelable {

}