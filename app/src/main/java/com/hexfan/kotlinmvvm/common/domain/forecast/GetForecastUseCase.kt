package com.hexfan.kotlinmvvm.common.domain

import com.hexfan.kotlinmvvm.common.network.service.ForecastService
import com.hexfan.kotlinmvvm.common.domain.model.Forecast
import io.reactivex.Flowable
import javax.inject.Inject

open class GetForecastUseCase @Inject constructor(private val forecastService: ForecastService) {

    open fun execute(latitude: Double = 0.0, longitude: Double = 0.0): Flowable<Forecast> {
        return forecastService.getForecast(latitude, longitude)
                .onErrorResumeNext(Flowable.empty())
    }
}