package com.parohy.weatherapp

import android.app.Application
import android.widget.EditText
import com.parohy.weatherapp.api.dagger.AppComponent

fun Application.getAppComponent(): AppComponent = (this as WeatherApplication).getAppComponent()

fun EditText.value(): String = text.toString()

