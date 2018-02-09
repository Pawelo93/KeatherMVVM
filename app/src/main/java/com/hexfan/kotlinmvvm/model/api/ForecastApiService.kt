package com.hexfan.kotlinmvvm.model.api

import com.hexfan.kotlinmvvm.model.pojo.Forecast
import com.hexfan.kotlinmvvm.model.pojo.Weather
import io.reactivex.Flowable

/**
 * Created by Pawel on 11.12.2017.
 */
class ForecastApiService(private val openWeatherMapService: OpenWeatherMapService) : ForecastService {

    override fun getForecast(latitude: Double, longitude: Double): Flowable<Forecast> {
        return openWeatherMapService.getForecast(latitude, longitude).map {
            val iconUrl = "http://openweathermap.org/img/w/${it.weather[0].icon}.png"
            val weather = Weather(it.main.temp.toInt(),
                    it.main.pressure.toInt(),
                    it.main.humidity, it.wind.speed,
                    it.weather.firstOrNull()?.main ?: "",
                    iconUrl
            )
            Forecast(weather, it.name)
        }
    }

}
