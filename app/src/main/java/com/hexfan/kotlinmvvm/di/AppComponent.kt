package com.hexfan.kotlinmvvm.di

import com.hexfan.kotlinmvvm.ui.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
* Created by Paweł Antonik on 28.11.2017.
*/

@Singleton
@Component(modules = arrayOf(AppModule::class, ActivityBuilder::class, AndroidSupportInjectionModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: MainApplication)
}