package com.hexfan.kotlinmvvm.common.domain.model

data class Weather(
        val temperature: Int,
        val pressure: Int,
        val humidity: Int,
        val windSpeed: Double,
        val description: String,
        val iconUrl: String
)
