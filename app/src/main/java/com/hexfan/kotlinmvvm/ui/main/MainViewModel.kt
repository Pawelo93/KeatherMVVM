package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.location.Location
import com.hexfan.kotlinmvvm.common.domain.GetTodayForecastUseCase
import com.hexfan.kotlinmvvm.common.rx.RxTransformer
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class MainViewModel @Inject constructor(val getTodayForecastUseCase: GetTodayForecastUseCase,
                                             val rxTransformer: RxTransformer) : ViewModel() {

    var todayForecast = MutableLiveData<Forecast>()
    val compositeDisposable = CompositeDisposable()

    fun loadForecast(latitude: Double, longitude: Double) {
        getTodayForecastUseCase.execute(latitude, longitude)
                .compose(rxTransformer.single())
                .subscribe({
                    todayForecast.postValue(it)
                    loadYesterdayForecast(it.cityId)
                }, {
                    println("Error ${it.message}")
                }).remember()
    }

    fun loadYesterdayForecast(cityId: Int) {
//        getTodayForecastUseCase.execute(location.latitude, location.longitude)
//                .compose(rxTransformer.flowable())
//                .subscribe {
//                    todayForecast.postValue(it)
//                    loadYesterdayForecast(it.cityId)
//                }.remember()
    }

    fun Disposable.remember() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    open class Factory @Inject constructor(private val getTodayForecastUseCase: GetTodayForecastUseCase,
                                           private val rxTransformer: RxTransformer) : ViewModelProvider.Factory {

        open override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(getTodayForecastUseCase, rxTransformer) as T
        }
    }
}