package com.example.weather.service

import com.example.weather.model.WeatherData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
class WeatherService {
    private val objectMapper = ObjectMapper()

    fun parseWeatherData(response: String?, day: Int): List<WeatherData>? {
        if (response.isNullOrBlank()) return null

        val rootNode = objectMapper.readTree(response) ?: return null
        val forecastsNode = rootNode["list"] ?: return null

        val weatherDataList = mutableListOf<WeatherData>()
        for (forecastNode in forecastsNode) {
            val forecastDay = forecastNode["dt"].asLong() // Convert to Long
            if (forecastDay == day.toLong()) { // Convert day to Long for comparison
                val temperature = forecastNode["temp"]["day"].asDouble().toFloat() // Temperature is a float
                weatherDataList.add(WeatherData (temperature,forecastDay,))
            }
        }
        return weatherDataList
    }
}