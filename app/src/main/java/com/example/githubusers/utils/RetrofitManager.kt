package com.example.githubusers.utils

import com.example.githubusers.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun getInstance(): MyAPIService? {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.SERVER_URL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
        return retrofit.create(MyAPIService::class.java)
    }
}