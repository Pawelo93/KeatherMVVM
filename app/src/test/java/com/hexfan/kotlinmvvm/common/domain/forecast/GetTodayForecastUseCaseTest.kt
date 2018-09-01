package com.hexfan.kotlinmvvm.common.domain.forecast

import com.hexfan.kotlinmvvm.common.domain.GetTodayForecastUseCase
import com.hexfan.kotlinmvvm.common.domain.model.ForecastResponse
import com.hexfan.kotlinmvvm.common.domain.model.MainWeatherResponse
import com.hexfan.kotlinmvvm.common.domain.model.WeatherResponse
import com.hexfan.kotlinmvvm.common.domain.model.WindResponse
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetTodayForecastUseCaseTest {

    lateinit var getTodayForecastUseCase: GetTodayForecastUseCase
    @Mock
    lateinit var openWeatherMapService: OpenWeatherMapService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getTodayForecastUseCase = GetTodayForecastUseCase(openWeatherMapService)
    }

    @Test
    fun testExecute() {
        val latitude = 0.0
        val longitude = 0.0
        val forecastResponse = ForecastResponse(
                0,
                "test",
                WindResponse(2.0),
                listOf(WeatherResponse("10", "", "")),
                MainWeatherResponse(0.0, 0.0, 0)
        )

        whenever(openWeatherMapService.getTodayForecast(latitude, longitude)).thenReturn(Single.just(forecastResponse))

        getTodayForecastUseCase.execute(latitude, longitude).test()
                .assertComplete()
                .assertNoErrors()
                .assertValue {
                    it.cityId == 0
                }
    }
}