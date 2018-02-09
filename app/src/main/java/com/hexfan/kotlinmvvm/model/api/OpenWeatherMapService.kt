package com.hexfan.kotlinmvvm.model.api

import com.hexfan.kotlinmvvm.model.pojo.ForecastResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pawel on 11.12.2017.
 */
interface OpenWeatherMapService {

    @GET("weather")
    fun getForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Flowable<ForecastResponse>

}
