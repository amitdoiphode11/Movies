package com.eaglesoft.movies.business.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeGenre(
    var id: Int = 0,
    var name: String? = null
) : Parcelable