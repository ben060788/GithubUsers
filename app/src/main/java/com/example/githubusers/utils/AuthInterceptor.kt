package com.example.githubusers.utils

import com.example.githubusers.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor() : Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val reqBuilder = chain.request().newBuilder()
        reqBuilder.addHeader("Authorization", "Bearer " + BuildConfig.ACCESS_TOKEN)
        reqBuilder.addHeader("accept", "application/vnd.github+json")
        return chain.proceed(reqBuilder.build())
    }
}