package com.hexfan.kotlinmvvm

import android.app.Activity
import android.app.Application
import com.hexfan.kotlinmvvm.ui.main.MainApplication
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
class TestApplication : Application(), HasActivityInjector {

    lateinit var injector: AndroidInjector<Activity>

    override fun activityInjector() = injector
}