package com.parohy.weatherapp.api.model

data class Weather(
    var degreesCurrent: Degree = Degree(0.0),
    var degreesMin: Degree = Degree(0.0),
    var degreesMax: Degree = Degree(0.0),
    var description: String = "",
    var icon: String = "",
    var name: String = ""
) {
    companion object {
        val TAG: String = Weather::class.java.simpleName
    }
}