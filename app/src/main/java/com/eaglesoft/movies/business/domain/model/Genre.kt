package com.eaglesoft.movies.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    var id: Int = 0,
    var name: String? = null
) : Parcelable