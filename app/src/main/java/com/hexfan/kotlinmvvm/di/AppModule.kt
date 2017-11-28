package com.hexfan.kotlinmvvm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
@Module
class AppModule {

    @Provides
    fun applicationContext(application: Application): Context{
        return application
    }
}