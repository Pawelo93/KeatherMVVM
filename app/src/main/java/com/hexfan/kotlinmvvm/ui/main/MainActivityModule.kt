package com.hexfan.kotlinmvvm.di.module

import com.hexfan.kotlinmvvm.common.domain.GetTodayForecastUseCase
import com.hexfan.kotlinmvvm.common.mappers.ForecastMapper
import com.hexfan.kotlinmvvm.common.rx.IOTransformer
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
    fun provideForecastInteractor(forecastService: OpenWeatherMapService, forecastMapper: ForecastMapper): GetTodayForecastUseCase {
        return GetTodayForecastUseCase(forecastService, forecastMapper)
    }

    @Provides
    fun provideMainViewModelFactory(getTodayForecastUseCase: GetTodayForecastUseCase,
                                    ioTransformer: IOTransformer): MainViewModel.Factory {
        return MainViewModel.Factory(getTodayForecastUseCase, ioTransformer)
    }

    @Provides
    fun provideReactiveLocationProvider(activity: MainActivity): ReactiveLocationProvider {
        return ReactiveLocationProvider(activity)
    }

}