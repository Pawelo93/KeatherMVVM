package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.*
import android.location.Location
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import com.hexfan.kotlinmvvm.model.interactors.ForecastInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Paweł Antonik on 28.11.2017.
 */
open class MainViewModel @Inject constructor(val forecastInteractor: ForecastInteractor) : ViewModel(){

    var forecast = MutableLiveData<Forecast>()

    fun loadForecast(location: Location){
        forecastInteractor.execute(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    forecast.postValue(it)
                }
    }

    open class Factory @Inject constructor(private val forecastInteractor: ForecastInteractor) : ViewModelProvider.Factory{

        open override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(forecastInteractor) as T
        }
    }
}