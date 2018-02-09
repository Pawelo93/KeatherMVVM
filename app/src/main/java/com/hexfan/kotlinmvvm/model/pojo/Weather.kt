package com.hexfan.kotlinmvvm.model.pojo

/**
 * Created by Pawel on 11.12.2017.
 */
data class Weather(
        val temperature: Int,
        val pressure: Int,
        val humidity: Int,
        val windSpeed: Double,
        val description: String,
        val iconUrl: String
)
