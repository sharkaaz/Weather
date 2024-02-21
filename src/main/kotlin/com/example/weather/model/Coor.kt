package com.example.weather.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Coor (
    @JsonProperty("lat")
    val lat: Double,
    @JsonProperty("lon")
    val lon: Double
)