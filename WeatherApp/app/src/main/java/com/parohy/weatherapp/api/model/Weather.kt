package com.parohy.weatherapp.api.model

data class Weather(
    var degreesCurrent: Double = 0.0,
    var degreesMin: Double = 0.0,
    var degreesMax: Double = 0.0,
    var description: String = "",
    var icon: String = ""
) {
    companion object {
        val TAG: String = Weather::class.java.simpleName
    }
}