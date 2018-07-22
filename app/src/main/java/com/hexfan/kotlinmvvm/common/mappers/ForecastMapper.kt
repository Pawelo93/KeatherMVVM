package com.hexfan.kotlinmvvm.common.mappers

import com.hexfan.kotlinmvvm.common.domain.model.ForecastResponse
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import com.hexfan.kotlinmvvm.model.pojo.Weather

class ForecastMapper {

    fun execute(forecastResponse: ForecastResponse): Forecast {
        val iconUrl = "http://openweathermap.org/img/w/${forecastResponse.weather[0].icon}.png"
        val weather = Weather(forecastResponse.main.temp.toInt(),
                forecastResponse.main.pressure.toInt(),
                forecastResponse.main.humidity, forecastResponse.wind.speed,
                forecastResponse.weather.firstOrNull()?.main ?: "",
                iconUrl
        )
        return Forecast(weather, forecastResponse.name)
    }
}