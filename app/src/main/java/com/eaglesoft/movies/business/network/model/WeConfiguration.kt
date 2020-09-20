package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeConfiguration(
    var images: WeImages? = null,
    var changeKeys: List<String>? = null
) : Parcelable