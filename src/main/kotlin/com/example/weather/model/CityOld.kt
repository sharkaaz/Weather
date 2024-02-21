package com.example.weather.model

import com.fasterxml.jackson.annotation.JsonProperty

class CityOld (
    @JsonProperty("name")
    val name: String,
    @JsonProperty("coor")
    val coor: Coor,
)