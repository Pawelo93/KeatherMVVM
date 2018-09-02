package com.hexfan.kotlinmvvm.common.network.service

import com.hexfan.kotlinmvvm.common.network.response.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather?units=metric")
    fun getTodayForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Single<ForecastResponse>
}
