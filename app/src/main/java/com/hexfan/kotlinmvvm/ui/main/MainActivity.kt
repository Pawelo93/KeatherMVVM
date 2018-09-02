package com.hexfan.kotlinmvvm.ui.main

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexfan.kotlinmvvm.R
import com.hexfan.kotlinmvvm.common.domain.model.Forecast
import com.hexfan.kotlinmvvm.utils.PermissionsManager
import com.hexfan.kotlinmvvm.utils.ReactiveLocationProvider
import com.hexfan.kotlinmvvm.utils.observe
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.today_weather_view.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModel.Factory
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var reactiveLocationProvider: ReactiveLocationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        PermissionsManager.need(this, Manifest.permission.ACCESS_FINE_LOCATION, object : PermissionsManager.Callback {
            override fun permissionGranted(requestCode: Int) {
                reactiveLocationProvider.loadLocation()
            }

            override fun permissionCanceled() {
                finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.todayForecast.observe(this) {
            onTodayForecastProvided(it)
        }

        reactiveLocationProvider.locations.observe(this) {
            Timber.d("got location : $it")
            viewModel.loadForecast(it.latitude, it.longitude)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun onTodayForecastProvided(forecast: Forecast?) {
        println(forecast)
        forecast?.let {
            val (id, weather, city) = forecast
            textViewCity.text = city
            textViewTemp.text = resources.getString(R.string.temperature, weather.temperature)
            textViewWindSpeed.text = resources.getString(R.string.wind_speed, weather.windSpeed)
            textViewHumidity.text = getString(R.string.humidity, weather.humidity)
            textViewPressure.text = getString(R.string.pressure, weather.pressure)

        }
    }

}
