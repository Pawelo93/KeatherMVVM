package com.hexfan.kotlinmvvm.common.network.response

data class ForecastResponse(
        val id: Int,
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
