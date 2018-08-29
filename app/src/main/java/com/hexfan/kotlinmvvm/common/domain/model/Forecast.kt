package com.hexfan.kotlinmvvm.model.pojo

data class Forecast(
        val cityId: Int,
        val weather: Weather,
        val city: String
)
