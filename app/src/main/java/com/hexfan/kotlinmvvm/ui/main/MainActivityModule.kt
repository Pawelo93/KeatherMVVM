package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModel(mainActivity: MainActivity, factory: MainViewModel.Factory): MainViewModel{
        return ViewModelProviders.of(mainActivity, factory).get(MainViewModel::class.java)
    }

    @Provides
    fun provideMainViewModelFactory(): MainViewModel.Factory{
        return MainViewModel.Factory()
    }
}