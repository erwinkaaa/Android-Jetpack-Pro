package com.example.moviecatalogue.repository.remote.api

import com.example.moviecatalogue.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiRepository {

    private const val BASE_URL: String = BuildConfig.BASE_URL
    const val IMAGE_URL: String = BuildConfig.IMAGE_BASE_URL

    fun retrofitApiServiceInstance(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build().create(ApiService::class.java)
    }

    private fun interceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor())
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}