package com.parohy.weatherapp.api.model

data class Weather(
    private val degreesCurrent: Double,
    private val degreesMin: Double,
    private val degreesMax: Double,
    private val description: String,
    private val icon: String
) {
    companion object {
        val TAG: String = Weather::class.java.simpleName
    }
}