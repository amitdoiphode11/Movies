package com.eaglesoft.movies.framework.base

import android.app.Application

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
    }


}

