package com.parohy.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.api.model.Weather
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    companion object {
        val TAG: String = WeatherViewModel::class.java.simpleName
    }

    fun getWeather(city: String) = weatherRepository.getWeather(city)

    fun observe(): LiveData<Resource<Weather>> = weatherRepository.observeRepoData()
}