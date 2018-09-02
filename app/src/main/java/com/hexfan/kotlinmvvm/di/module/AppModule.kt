package com.hexfan.kotlinmvvm.di.module

import android.app.Application
import android.content.Context
import com.hexfan.kotlinmvvm.common.rx.IOTransformer
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun applicationContext(application: Application): Context {
        return application
    }

    @Provides
    fun ioTransformer() = IOTransformer()
}