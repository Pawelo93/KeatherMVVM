package com.hexfan.kotlinmvvm.di.module

import com.hexfan.kotlinmvvm.model.api.ForecastApiService
import com.hexfan.kotlinmvvm.common.network.service.ForecastService
import com.hexfan.kotlinmvvm.model.api.OpenWeatherMapService
import com.hexfan.kotlinmvvm.common.domain.GetForecastUseCase
import com.hexfan.kotlinmvvm.ui.main.MainActivity
import com.hexfan.kotlinmvvm.ui.main.MainViewModel
import com.hexfan.kotlinmvvm.utils.ReactiveLocationProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideForecastApiService(service: OpenWeatherMapService): ForecastService {
        return ForecastApiService(service)
    }

    @Provides
    fun provideForecastInteractor(forecastService: ForecastService): GetForecastUseCase {
        return GetForecastUseCase(forecastService)
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