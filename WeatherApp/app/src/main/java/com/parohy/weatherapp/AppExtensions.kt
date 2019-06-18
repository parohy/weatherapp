package com.parohy.weatherapp

import android.app.Application
import com.parohy.weatherapp.api.dagger.AppComponent

fun Application.getAppComponent(): AppComponent = (this as WeatherApplication).getAppComponent()