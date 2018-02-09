package com.hexfan.kotlinmvvm.di

import com.hexfan.kotlinmvvm.ui.main.MainActivity
import com.hexfan.kotlinmvvm.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel


}