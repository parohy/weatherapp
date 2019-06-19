package com.parohy.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.api.model.Weather
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    companion object {
        val TAG: String = WeatherViewModel::class.java.simpleName
    }

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

    fun getWeather(city: String) = weatherRepository.getWeather(city)

    fun get(): LiveData<Resource<Weather>> = weatherData

}