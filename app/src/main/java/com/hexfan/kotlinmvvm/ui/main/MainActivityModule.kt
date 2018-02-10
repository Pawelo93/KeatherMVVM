package com.hexfan.kotlinmvvm.ui.main

import com.hexfan.kotlinmvvm.utils.ReactiveLocationProvider
import com.hexfan.kotlinmvvm.model.api.ForecastApiService
import com.hexfan.kotlinmvvm.model.api.ForecastService
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import com.hexfan.kotlinmvvm.model.interactors.ForecastInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@Module
class MainActivityModule {

    @Provides
    fun provideForecastApiService(service: OpenWeatherMapService): ForecastService {
        return ForecastApiService(service)
    }

    @Provides
    fun provideForecastInteractor(forecastService: ForecastService): ForecastInteractor {
        return ForecastInteractor(forecastService)
    }

    @Provides
    fun provideMainViewModelFactory(forecastInteractor: ForecastInteractor): MainViewModel.Factory{
        return MainViewModel.Factory(forecastInteractor)
    }

    @Provides
    fun provideReactiveLocationProvider(activity: MainActivity): ReactiveLocationProvider {
        return ReactiveLocationProvider(activity)
    }

}