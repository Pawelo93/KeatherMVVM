package com.hexfan.kotlinmvvm.di.component

import com.hexfan.kotlinmvvm.common.network.NetworkModule
import com.hexfan.kotlinmvvm.di.module.AppModule
import com.hexfan.kotlinmvvm.di.module.ActivityModule
import com.hexfan.kotlinmvvm.di.module.FragmentModule
import com.hexfan.kotlinmvvm.ui.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ActivityModule::class, FragmentModule::class, AndroidSupportInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: MainApplication)
}