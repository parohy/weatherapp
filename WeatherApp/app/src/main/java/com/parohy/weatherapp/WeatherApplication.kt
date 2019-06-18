package com.parohy.weatherapp

import android.app.Application
import com.parohy.weatherapp.api.dagger.AppComponent
import com.parohy.weatherapp.api.dagger.DaggerAppComponent

class WeatherApplication: Application() {
    companion object {
        val TAG: String = WeatherApplication::class.java.simpleName
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent = appComponent
}