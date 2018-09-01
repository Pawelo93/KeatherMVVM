package com.hexfan.kotlinmvvm.model.api

import com.hexfan.kotlinmvvm.common.domain.model.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather?units=metric")
    fun getTodayForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Single<ForecastResponse>
}
