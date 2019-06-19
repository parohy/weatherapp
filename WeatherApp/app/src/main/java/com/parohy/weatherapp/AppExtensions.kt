package com.parohy.weatherapp

import android.app.Application
import android.widget.EditText
import com.parohy.weatherapp.api.dagger.AppComponent
import com.parohy.weatherapp.ui.viewmodel.ViewModelFactory

fun Application.getAppComponent(): AppComponent = (this as WeatherApplication).getAppComponent()

fun Application.getViewModelFactory(): ViewModelFactory = (this as WeatherApplication).getFactory()

fun EditText.value(): String = text.toString()

