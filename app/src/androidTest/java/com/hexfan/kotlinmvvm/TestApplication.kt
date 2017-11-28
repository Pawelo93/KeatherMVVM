package com.hexfan.kotlinmvvm

import android.app.Activity
import android.app.Application
import android.support.test.InstrumentationRegistry
import com.hexfan.kotlinmvvm.ui.main.MainApplication
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
class TestApplication : MainApplication() {

    lateinit var injector: AndroidInjector<Activity>

    override fun activityInjector() = injector

    companion object {
        fun setInjector(function: (Activity) -> Unit) {
            (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).injector = AndroidInjector {
                function(it)
            }
        }
    }
}