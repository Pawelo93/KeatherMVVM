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

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
@Module
class AppModule {

    @Provides
    fun applicationContext(application: Application): Context{
        return application
    }

    @Provides
    @Singleton
    fun retrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }


    @Provides
    @Singleton
    fun openWeatherMapService(retrofit: Retrofit): OpenWeatherMapService {
        return retrofit.create(OpenWeatherMapService::class.java)
    }


    @Provides
    @Singleton
    fun authorizingInterceptor(): AuthorizingInterceptor {
        return AuthorizingInterceptor()
    }

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @Singleton
    fun httpClient(authorizingInterceptor: AuthorizingInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(authorizingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }


}