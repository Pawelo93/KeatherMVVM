package com.hexfan.kotlinmvvm.common.domain

import com.hexfan.kotlinmvvm.common.mappers.ForecastMapper
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import io.reactivex.Flowable
import javax.inject.Inject

open class GetForecastUseCase @Inject constructor(private val openWeatherMapService: OpenWeatherMapService,
                                                  private val forecastMapper: ForecastMapper) {

    open fun execute(latitude: Double = 0.0, longitude: Double = 0.0): Flowable<Forecast> {
        return openWeatherMapService.getForecast(latitude, longitude)
                .map(forecastMapper::execute)
                .onErrorResumeNext(Flowable.empty())
    }
}