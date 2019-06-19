package com.parohy.weatherapp.api.rest

import com.parohy.weatherapp.api.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather?units=metric")
    fun fetch(@Query("appId") key: String, @Query("q") city: String): Call<Weather>
}