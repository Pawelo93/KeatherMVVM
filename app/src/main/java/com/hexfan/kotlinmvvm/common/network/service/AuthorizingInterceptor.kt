package com.hexfan.kotlinmvvm.common.network.service

import com.hexfan.kotlinmvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val newUrl = originalUrl.newBuilder()
                .addQueryParameter("APPID", BuildConfig.API_KEY)
                .build()

        val builder = originalRequest.newBuilder()
                .url(newUrl)

        return chain.proceed(builder.build())
    }
}