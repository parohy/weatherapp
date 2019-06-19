package com.parohy.weatherapp.api.model

data class Degree(val temp: Double) {
    companion object {
        val TAG: String = Degree::class.java.simpleName
    }

    fun celsius(): Double = temp - 273.15
}