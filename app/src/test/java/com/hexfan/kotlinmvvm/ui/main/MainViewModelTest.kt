package com.hexfan.kotlinmvvm.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.hexfan.kotlinmvvm.TestTransformer
import com.hexfan.kotlinmvvm.common.domain.forecast.GetTodayForecastUseCase
import com.hexfan.kotlinmvvm.common.domain.model.Forecast
import com.hexfan.kotlinmvvm.common.domain.model.Weather
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel
    @Mock
    lateinit var getTodayForecastUseCase: GetTodayForecastUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(getTodayForecastUseCase, TestTransformer())
    }

    @Test
    fun todayForecast_type1() {
        val latitude = 5.0
        val longitude = 10.0
        val forecast = Forecast(1, Weather(0, 0, 0, 0.0, "", ""), "Test")
        whenever(getTodayForecastUseCase.execute(latitude, longitude)).thenReturn(Single.just(forecast))

        viewModel.loadForecast(latitude, longitude)

        assertEquals(forecast, viewModel.todayForecast.value)
    }

    @Test
    fun todayForecast_type2() {
        val latitude = 5.0
        val longitude = 10.0
        val forecast = Forecast(1, Weather(0, 0, 0, 0.0, "", ""), "Test")
        val observer = mock<Observer<Forecast>>()
        Mockito.`when`(getTodayForecastUseCase.execute(latitude, longitude)).thenReturn(Single.just(forecast))

        viewModel.todayForecast.observeForever(observer)
        viewModel.loadForecast(latitude, longitude)

        verify(observer).onChanged(forecast)
    }
}