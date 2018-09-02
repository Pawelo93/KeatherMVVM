package com.hexfan.kotlinmvvm.common.domain.forecast

import com.hexfan.kotlinmvvm.common.network.response.ForecastResponse
import com.hexfan.kotlinmvvm.common.network.service.OpenWeatherMapService
import com.hexfan.kotlinmvvm.common.domain.model.Forecast
import com.hexfan.kotlinmvvm.common.domain.model.Weather
import io.reactivex.Single
import javax.inject.Inject

open class GetTodayForecastUseCase @Inject constructor(private val openWeatherMapService: OpenWeatherMapService) {

    open fun execute(latitude: Double = 0.0, longitude: Double = 0.0): Single<Forecast> {
        return openWeatherMapService.getTodayForecast(latitude, longitude)
                .map(::mapper)
    }

    fun mapper(forecastResponse: ForecastResponse): Forecast {
        val iconUrl = "http://openweathermap.org/img/w/${forecastResponse.weather[0].icon}.png"
        val weather = Weather(forecastResponse.main.temp.toInt(),
                forecastResponse.main.pressure.toInt(),
                forecastResponse.main.humidity, forecastResponse.wind.speed,
                forecastResponse.weather.firstOrNull()?.main ?: "",
                iconUrl
        )
        return Forecast(forecastResponse.id, weather, forecastResponse.name)
    }
}