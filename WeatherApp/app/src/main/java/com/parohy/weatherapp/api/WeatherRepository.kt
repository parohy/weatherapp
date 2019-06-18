package com.parohy.weatherapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.parohy.weatherapp.api.model.Weather
import com.parohy.weatherapp.api.rest.Remote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val remote: Remote) {
    companion object {
        val TAG: String = WeatherRepository::class.java.simpleName
    }

    private val data: MediatorLiveData<Resource<Weather>> = MediatorLiveData()

    fun getWeather(city: String) {
        val remoteData = remote.weatherInCity(city)
        data.addSource(remoteData) {
            data.value = it
            //TODO: If there were persistence or DB pass result and return from DB as single truth
        }
    }

    fun observeRepoData(): LiveData<Resource<Weather>> = data
}