package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
open class MainViewModel : ViewModel(){

    private val forecastData = MutableLiveData<String>()

    open fun getForecast(): LiveData<String>{
        return forecastData
    }

    open class Factory @Inject constructor() : ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}