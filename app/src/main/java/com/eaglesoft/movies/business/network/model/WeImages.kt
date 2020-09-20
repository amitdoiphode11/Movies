package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeImages(
    var baseUrl: String? = null,
    var secureBaseUrl: String? = null,
    var backdropSizes: List<String>? = null,
    var logoSizes: List<String>? = null,
    var posterSizes: List<String>? = null,
    var profileSizes: List<String>? = null,
    var stillSizes: List<String>? = null
) : Parcelable