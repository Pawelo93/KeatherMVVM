package com.hexfan.kotlinmvvm.model.pojo

/**
 * Created by Pawel on 11.12.2017.
 */

data class ForecastResponse(
        val name: String,
        val wind: WindResponse,
        val weather: List<WeatherResponse>,
        val main: MainWeatherResponse
)

data class WindResponse(
        val speed: Double
)

data class MainWeatherResponse(
        val temp: Double,
        val pressure: Double,
        val humidity: Int
)

data class WeatherResponse(
        val main: String,
        val description: String,
        val icon: String
)
