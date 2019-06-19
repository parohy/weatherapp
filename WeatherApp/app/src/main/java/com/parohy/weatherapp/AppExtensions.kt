package com.parohy.weatherapp

import android.app.Application
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.parohy.weatherapp.api.dagger.AppComponent
import com.parohy.weatherapp.ui.viewmodel.ViewModelFactory

fun Application.getAppComponent(): AppComponent = (this as WeatherApplication).getAppComponent()

fun Application.getViewModelFactory(): ViewModelFactory = (this as WeatherApplication).getFactory()

fun EditText.value(): String = text.toString()

fun String.capFirst(): String = substring(0, 1).toUpperCase() + substring(1)

