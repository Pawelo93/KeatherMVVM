package com.hexfan.kotlinmvvm.ui.main

import android.app.Activity
import android.app.Application
import com.hexfan.kotlinmvvm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
open class MainApplication : Application(), HasActivityInjector {

    @Inject
    protected lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}