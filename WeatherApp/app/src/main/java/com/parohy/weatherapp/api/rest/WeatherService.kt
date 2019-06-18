package com.parohy.weatherapp.api.rest

import com.parohy.weatherapp.api.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/weather?location={city}&appid=7587eaff3affbf8e56a81da4d6c51d06")
    fun fetch(@Query("city") city: String): Call<Weather>
}