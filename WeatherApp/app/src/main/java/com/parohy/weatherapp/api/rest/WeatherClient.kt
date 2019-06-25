package com.parohy.weatherapp.api.rest

import com.google.gson.GsonBuilder
import com.parohy.weatherapp.BuildConfig
import com.parohy.weatherapp.api.model.Weather
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherClient @Inject constructor() {
    companion object {
        val TAG: String = WeatherClient::class.java.simpleName
    }

    private var retrofitClient: Retrofit
    private var weatherService: WeatherService

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Weather::class.java, WeatherDeserializer())

        retrofitClient = Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        weatherService = retrofitClient.create(WeatherService::class.java)
    }

    fun getWeatherService(): WeatherService = weatherService
}