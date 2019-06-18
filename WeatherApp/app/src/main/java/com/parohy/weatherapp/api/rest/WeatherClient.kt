package com.parohy.weatherapp.api.rest

import com.google.gson.GsonBuilder
import com.parohy.weatherapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherClient @Inject constructor() {
    companion object {
        val TAG: String = WeatherClient::class.java.simpleName
    }

    private val gsonBuilder = GsonBuilder()

    private  val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.setLenient().create()))
        .build()

    private val weatherService = retrofitClient.create(WeatherService::class.java)

    fun getWeatherService() = weatherService
}