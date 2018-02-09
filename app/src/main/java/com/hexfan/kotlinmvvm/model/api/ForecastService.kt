package com.hexfan.kotlinmvvm.model.api

import com.hexfan.kotlinmvvm.model.pojo.Forecast
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Pawel on 11.12.2017.
 */
interface ForecastService {

    fun getForecast(latitude: Double, longitude: Double): Flowable<Forecast>

}
