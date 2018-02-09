package com.hexfan.kotlinmvvm.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hexfan.kotlinmvvm.R
import com.hexfan.kotlinmvvm.model.pojo.Forecast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.hexfan.kotlinmvvm.observe
import com.squareup.picasso.Picasso

/**
* Created by Paweł Antonik on 28.11.2017.
*/
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: MainViewModel.Factory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        if(savedInstanceState == null) {
            println("load")
            viewModel.loadForecast()
        }
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
        viewModel.forecast.observe(this){
            onForecastProvided(it)
        }

    }

    private fun onForecastProvided(forecast: Forecast?) {
        if (forecast != null) {
            val (weather, city) = forecast
            temperatureTextView.text = getString(R.string.temperature, weather.temperature)
            cityTextView.text = city
            descriptionTextView.text = weather.description
            humidityTextView.text = getString(R.string.humidity, weather.humidity)
            pressureTextView.text = getString(R.string.pressure, weather.pressure)
            windTextView.text = getString(R.string.speed, weather.windSpeed)

            Picasso.with(this)
                    .load(weather.iconUrl)
                    .into(icon)

        } else {
            temperatureTextView.setText(R.string.na)
            cityTextView.setText(R.string.na)
            descriptionTextView.setText(R.string.na)
            humidityTextView.setText(R.string.na)
            pressureTextView.setText(R.string.na)
            windTextView.setText(R.string.na)
        }
    }

}
