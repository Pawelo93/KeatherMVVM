package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.location.Location
import com.hexfan.kotlinmvvm.common.domain.GetForecastUseCase
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainViewModel @Inject constructor(val getForecastUseCase: GetForecastUseCase) : ViewModel() {

    var forecast = MutableLiveData<Forecast>()

    fun loadForecast(location: Location) {
        val disposable = getForecastUseCase.execute(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    forecast.postValue(it)
                }
    }

    open class Factory @Inject constructor(private val getForecastUseCase: GetForecastUseCase) : ViewModelProvider.Factory {

        open override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(getForecastUseCase) as T
        }
    }
}