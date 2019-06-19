package com.parohy.weatherapp.ui.viewmodel

import android.text.format.DateFormat
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.parohy.weatherapp.api.Resource
import com.parohy.weatherapp.api.WeatherRepository
import com.parohy.weatherapp.api.model.Weather
import com.parohy.weatherapp.capFirst
import java.util.*
import javax.inject.Inject


class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    companion object {
        val TAG: String = WeatherViewModel::class.java.simpleName

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }

    var date = DateFormat.format("EEEE", Date()).toString()

    private val weatherData: LiveData<Resource<Weather>>
        get() = weatherRepository.observeRepoData()

    val location: LiveData<String> = Transformations.map(weatherData) {
        it.data?.name
    }

    val currentDegreeCelsius: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesCurrent?.celsius()?.toInt()
    }

    val min: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesMin?.celsius()?.toInt()
    }

    val max: LiveData<Int> = Transformations.map(weatherData) {
        it.data?.degreesMax?.celsius()?.toInt()
    }

    val weatherDescription: LiveData<String> = Transformations.map(weatherData) {
        it.data?.description?.capFirst()
    }

    val icon: LiveData<String> = Transformations.map(weatherData) {
        it.data?.icon
    }

    fun getWeather(city: String) {
        return weatherRepository.getWeather(city)
    }

    fun get(): LiveData<Resource<Weather>> = weatherData

}