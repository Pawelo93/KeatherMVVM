package com.hexfan.kotlinmvvm.di

import android.app.Application
import android.content.Context
import com.hexfan.kotlinmvvm.BuildConfig
import com.hexfan.kotlinmvvm.model.api.AuthorizingInterceptor
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun applicationContext(application: Application): Context {
        return application
    }


}