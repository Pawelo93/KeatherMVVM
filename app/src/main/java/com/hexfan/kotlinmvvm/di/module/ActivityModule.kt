package com.hexfan.kotlinmvvm.di.module

import com.hexfan.kotlinmvvm.ui.main.MainActivity
import com.hexfan.kotlinmvvm.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}