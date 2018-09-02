package com.hexfan.kotlinmvvm.common.domain.model

import com.hexfan.kotlinmvvm.common.domain.model.Weather

data class Forecast(
        val cityId: Int,
        val weather: Weather,
        val city: String
)
