package com.hexfan.kotlinmvvm.model.interactors

import com.hexfan.kotlinmvvm.model.pojo.Forecast
import com.hexfan.kotlinmvvm.model.api.ForecastService
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Pawel on 11.12.2017.
 */
open class ForecastInteractor @Inject constructor(private val forecastService: ForecastService) {

    open fun execute(lattitude: Double = 0.0, longitude: Double = 0.0): Flowable<Forecast>{
        return forecastService.getForecast(lattitude, longitude)
                .onErrorResumeNext(Flowable.empty())
    }
}