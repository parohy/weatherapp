package com.parohy.weatherapp.api.model

class Degree(private val value: Double) {
    companion object {
        val TAG: String = Degree::class.java.simpleName
        const val KELVIN_ONE_CELSIUS = 297
    }

    fun kelvin(): Double = value

    fun celsius(): Double = value / KELVIN_ONE_CELSIUS
}