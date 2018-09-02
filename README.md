# KeatherMVVM
Simple weather app written in Kotlin with using of MVVM architecture and Android Architecture Components
# Features
The app display current weather for your location with air speed, humidity and pressure.


<p float="left">
  <img src="https://raw.githubusercontent.com/Pawelo93/KeatherMVVM/master/images/screenshot_day.png" width="368" />
  <img src="https://raw.githubusercontent.com/Pawelo93/KeatherMVVM/master/images/screenshot_night.png" width="368" /> 
</p>

# Architecture
MVVM is a pattern which works nicely with Android ViewModel class. In oposite to MVP, ViewModel know nothing about View, so there are less interfaces. 

<img src="https://cdn-images-1.medium.com/max/1600/1*VLhXURHL9rGlxNYe9ydqVg.png" width="736"/>

App is using: 
* Dagger2 for dependency injection
* Retrofit with OKHttp for networking
* RxJava2 for observer pattern and threading
* Timber for logs

# Testing
* Unit tests
```
    @Test
    fun todayForecast_type1() {
        val latitude = 5.0
        val longitude = 10.0
        val forecast = Forecast(1, Weather(0, 0, 0, 0.0, "", ""), "Test")
        whenever(getTodayForecastUseCase.execute(latitude, longitude)).thenReturn(Single.just(forecast))

        viewModel.loadForecast(latitude, longitude)

        assertEquals(forecast, viewModel.todayForecast.value)
    }
```
* Integration tests
```
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

        whenever(openWeatherMapService.getTodayForecast(latitude, longitude))
                 .thenReturn(Single.just(forecastResponse))

        getTodayForecastUseCase.execute(latitude, longitude).test()
                .assertComplete()
                .assertNoErrors()
                .assertValue {
                    it.cityId == 0
                }
    }
```

* UI tests
```
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
```
