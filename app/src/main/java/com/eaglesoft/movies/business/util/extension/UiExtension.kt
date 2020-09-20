package com.eaglesoft.movies.business.util.extension

import android.content.Context
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat

fun Context.tintMenuIcon(item: MenuItem, @ColorRes color: Int) {
    val itemIcon = item.icon
    val iconWrapper = DrawableCompat.wrap(itemIcon)
    DrawableCompat.setTint(iconWrapper, resources.getColor(color))
    item.icon = iconWrapper
}

