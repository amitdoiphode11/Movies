package com.eaglesoft.movies.framework.base

import android.app.Application
import com.eaglesoft.movies.business.cache.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        private val TAG = "BaseApplication"
        private var instance: BaseApplication? = null

        fun getInstance(): BaseApplication? {
            return instance
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.getAppDataBase(this)
    }


}

