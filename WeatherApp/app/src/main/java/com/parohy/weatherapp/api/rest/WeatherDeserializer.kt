package com.parohy.weatherapp.api.rest

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.parohy.weatherapp.api.model.Weather
import java.lang.reflect.Type

class WeatherDeserializer: JsonDeserializer<Weather?> {
    companion object {
        val TAG: String = WeatherDeserializer::class.java.simpleName
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Weather? =
            json?.asJsonObject?.let {
                Weather().apply {
                    val baseDataObject = it.getAsJsonArray("weather")[0].asJsonObject
                    description = baseDataObject.get("description").asString
                    icon = baseDataObject.get("icon").asString

                    val mainDataObject = it.getAsJsonObject("main")
                    degreesCurrent = mainDataObject.get("temp").asDouble
                    degreesMin = mainDataObject.get("temp_min").asDouble
                    degreesMax = mainDataObject.get("temp_max").asDouble

                    name = it.get("name").asString
                }

            }
}