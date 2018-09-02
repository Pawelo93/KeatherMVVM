package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hexfan.kotlinmvvm.R
import com.hexfan.kotlinmvvm.TestApplication
import com.hexfan.kotlinmvvm.common.domain.forecast.GetTodayForecastUseCase
import com.hexfan.kotlinmvvm.common.domain.model.Forecast
import com.hexfan.kotlinmvvm.common.domain.model.Weather
import com.hexfan.kotlinmvvm.common.rx.IOTransformer
import com.hexfan.kotlinmvvm.common.rx.RxTransformer
import com.hexfan.kotlinmvvm.utils.ReactiveLocationProvider
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Mock
    lateinit var interactor: GetTodayForecastUseCase

    @Mock
    lateinit var viewModel: MainViewModel

    val data = MutableLiveData<Forecast>()

    val testCity = "TestCity"
    val forecast = Forecast(0, Weather(0, 0, 0, 0.0, "null", ""), testCity)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = ForecastViewModelMock(data, interactor, IOTransformer())
        TestApplication.setInjector {
            (it as? MainActivity)?.factory = ForecastViewModelFactoryMock(viewModel)
            (it as? MainActivity)?.reactiveLocationProvider = ReactiveLocationProvider(it)
        }

        whenever(interactor.execute(any(), any())).thenReturn(Single.just(forecast))
        activityRule.launchActivity(Intent())
    }

    @Test
    fun showText() {
        data.postValue(forecast)
        onView(withId(R.id.textViewCity)).check(matches(withText(testCity)))
    }

    private class ForecastViewModelFactoryMock(val viewModel: MainViewModel) : MainViewModel.Factory(mock(), IOTransformer()) {
        open override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }

    private class ForecastViewModelMock(var data: MutableLiveData<Forecast>, forecastInteractor: GetTodayForecastUseCase,
                                        rxTransformer: RxTransformer) : MainViewModel(forecastInteractor, rxTransformer) {

    }


}