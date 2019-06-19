package com.parohy.weatherapp

import com.parohy.weatherapp.api.model.Degree
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DegreeUnitTest {

    private lateinit var degreePlus: Degree
    private lateinit var degreeZero: Degree
    private lateinit var degreeNegative: Degree

    @Before
    fun initDegree() {
        degreePlus = Degree(301.9)
        degreeZero = Degree(273.15)
        degreeNegative = Degree(260.15)
    }

    @Test
    fun correctCelsiusConversion() {
        assertEquals("Celsius should be 28.75 +- 0.1", 28.75, degreePlus.celsius(), 0.1)
        assertEquals("Celsius should be 0 +- 0.1", 0.0, degreeZero.celsius(), 0.1)
        assertEquals("Celsius should be -13 +- 0.1", -13.0, degreeNegative.celsius(), 0.1)
    }
}
