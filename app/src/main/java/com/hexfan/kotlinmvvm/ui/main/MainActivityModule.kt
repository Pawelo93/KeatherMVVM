package com.hexfan.kotlinmvvm.di.module

import com.hexfan.kotlinmvvm.common.domain.GetForecastUseCase
import com.hexfan.kotlinmvvm.common.mappers.ForecastMapper
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import com.hexfan.kotlinmvvm.ui.main.MainActivity
import com.hexfan.kotlinmvvm.ui.main.MainViewModel
import com.hexfan.kotlinmvvm.utils.ReactiveLocationProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideForecastMapper() = ForecastMapper()

    @Provides
    fun provideForecastInteractor(forecastService: OpenWeatherMapService, forecastMapper: ForecastMapper): GetForecastUseCase {
        return GetForecastUseCase(forecastService, forecastMapper)
    }

    @Provides
    fun provideMainViewModelFactory(getForecastUseCase: GetForecastUseCase): MainViewModel.Factory {
        return MainViewModel.Factory(getForecastUseCase)
    }

    @Provides
    fun provideReactiveLocationProvider(activity: MainActivity): ReactiveLocationProvider {
        return ReactiveLocationProvider(activity)
    }

}