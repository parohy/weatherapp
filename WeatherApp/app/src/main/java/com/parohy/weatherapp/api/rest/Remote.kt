package com.parohy.weatherapp.api.rest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Remote @Inject constructor(private val weatherService: WeatherService) {
    companion object {
        val TAG: String = Remote::class.java.simpleName
    }

    fun weatherInCity(city: String): LiveData<Resource<Weather>> {
        val data = MutableLiveData<Resource<Weather>>()
        data.value = Resource.loading()
        weatherService.fetch(city).enqueue(object: Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                data.value = if (response.isSuccessful) {
                    Resource.success(response.body()!!)
                } else {
                    Resource.error(Throwable("Error code: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
            }
        })

        return data
    }
}