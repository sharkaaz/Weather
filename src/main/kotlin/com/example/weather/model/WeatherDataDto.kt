package com.example.weather.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*

val mapper = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
}
data class WeatherDataDto (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val city: City,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val cod: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val message: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val cnt: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val list: List<ListElement>
) {
    fun toJson() = mapper.writeValueAsString(this)

    companion object {
        fun fromJson(json: String) = mapper.readValue<WeatherDataDto>(json)
    }
}

data class City (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val coord: Coord,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val country: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val population: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val timezone: Long
)

data class Coord (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val lon: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val lat: Double
)

data class ListElement (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val dt: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val sunrise: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val sunset: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val temp: Temp,

    @get:JsonProperty("feels_like", required=true)@field:JsonProperty("feels_like", required=true)
    val feelsLike: FeelsLike,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val pressure: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val humidity: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val weather: List<Weather>,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val speed: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val deg: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val gust: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val clouds: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val pop: Double,

    val rain: Double? = null
)

data class FeelsLike (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val day: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val night: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val eve: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val morn: Double
)

data class Temp (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val day: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val min: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val max: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val night: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val eve: Double,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val morn: Double
)

data class Weather (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val main: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val description: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val icon: String
)
