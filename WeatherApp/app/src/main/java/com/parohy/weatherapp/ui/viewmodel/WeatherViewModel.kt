package com.parohy.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.api.model.Weather
import android.text.format.DateFormat
import java.util.*
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    companion object {
        val TAG: String = WeatherViewModel::class.java.simpleName
    }

    var searchCity: String = ""
    var date = DateFormat.format("EEEE", Date()).toString()

    private val weatherData: LiveData<Resource<Weather>>
        get() = weatherRepository.observeRepoData()

    val currentDegreeCelsius: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesCurrent?.toInt()
    }

    val min: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesMin?.toInt()
    }

    val max: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesMax?.toInt()
    }

    val weatherDescription: LiveData<String> = Transformations.map(weatherData) {
        it.data?.description
    }

    val icon: LiveData<String> = Transformations.map(weatherData) {
        it.data?.icon
    }

    fun getWeather(city: String) {
        searchCity = city
        return weatherRepository.getWeather(city)
    }

    fun get(): LiveData<Resource<Weather>> = weatherData

}