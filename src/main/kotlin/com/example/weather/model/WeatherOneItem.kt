package com.example.weather.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class WeatherOneItem (
        @JsonProperty("temp")
        val temp: Int,
        @JsonProperty("dt")
        val dt: LocalDate
)