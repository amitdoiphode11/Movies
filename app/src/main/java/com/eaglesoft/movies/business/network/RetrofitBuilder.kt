package com.eaglesoft.movies.business.network

import android.util.Log
import com.eaglesoft.movies.BuildConfig
import com.eaglesoft.movies.business.network.services.ApiService
import com.eaglesoft.movies.framework.base.BaseApplication
import com.eaglesoft.movies.framework.error.ErrorHandlingActivity
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitBuilder {
    private val TAG = "RetrofitBuilder"
    private const val BASE_URL = "https://api.themoviedb.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private var clientInterceptor: Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        val url: HttpUrl =
            request.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }


    private var errorInterceptor = run {
        object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val response: Response = chain.proceed(request)

                if (response.code in 400..500) {
                    BaseApplication.getInstance()?.startActivity(
                        ErrorHandlingActivity.getStartIntent(
                            BaseApplication.getInstance(),
                            response.code
                        )
                    )
                    Log.e(TAG, "intercept: " + response.code)
                    //return response
                }
                return response
            }
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
        .addNetworkInterceptor(clientInterceptor)
        .addInterceptor(errorInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}