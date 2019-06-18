package com.parohy.weatherapp

import android.app.Application

class WeatherApplication: Application() {
    companion object {
        val TAG: String = WeatherApplication::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
    }
}