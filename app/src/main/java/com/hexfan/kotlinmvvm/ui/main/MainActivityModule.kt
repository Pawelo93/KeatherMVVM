package com.hexfan.kotlinmvvm.ui.main

import dagger.Module
import dagger.Provides

/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModelFactory(): MainViewModel.Factory{
        return MainViewModel.Factory()
    }
}