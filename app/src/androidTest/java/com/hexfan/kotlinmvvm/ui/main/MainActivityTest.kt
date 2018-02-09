package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.LiveData
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
import com.hexfan.kotlinmvvm.model.interactors.ForecastInteractor
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import com.hexfan.kotlinmvvm.model.pojo.Weather
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable


/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Mock
    lateinit var interactor: ForecastInteractor

    @Mock
    lateinit var viewModel: MainViewModel

    val data = MutableLiveData<Forecast>()

    val testCity = "TestCity"
    val forecast = Forecast(Weather(0, 0, 0, 0.0, "null"), testCity)
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        viewModel = ForecastViewModelMock(data, interactor)
        TestApplication.setInjector{
            (it as? MainActivity)?.factory = ForecastViewModelFactoryMock(viewModel)
        }

        whenever(interactor.execute()).thenReturn(Flowable.just(forecast))
        activityRule.launchActivity(Intent())
    }

    @Test
    fun showText(){
        data.postValue(forecast)
        onView(withId(R.id.cityTextView)).check(matches(withText(testCity)))
    }

    private class ForecastViewModelFactoryMock(val viewModel: MainViewModel) : MainViewModel.Factory(mock()) {
        open override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }

    private class ForecastViewModelMock(var data: MutableLiveData<Forecast>, forecastInteractor: ForecastInteractor) : MainViewModel(forecastInteractor) {

    }


}