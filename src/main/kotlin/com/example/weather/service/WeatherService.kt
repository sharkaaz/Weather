package com.example.weather.service

import com.example.weather.model.City
import com.example.weather.model.WeatherData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class WeatherService {
    private val objectMapper = ObjectMapper()

    fun parseWeatherData(response: String?, day: Int, city: City): List<WeatherData>? {
        if (response.isNullOrBlank()) return null

        val rootNode = objectMapper.readTree(response) ?: return null
        val forecastsNode = rootNode["list"] ?: return null

        val weatherDataList = mutableListOf<WeatherData>()

        val formatter = DateTimeFormatter.ofPattern("EEE dd/MM", Locale.ENGLISH)

        forecastsNode.forEach { forecastNode ->
            val forecastTimestamp = forecastNode["dt"].asLong() * 1000 // Convert seconds to milliseconds
            val forecastDate = Instant.ofEpochMilli(forecastTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()
            val temperature = forecastNode["temp"]["day"].asDouble().toFloat()
            val formattedDate = forecastDate.format(formatter)
            weatherDataList.add(WeatherData(temperature, forecastTimestamp, formattedDate))
        }

        // Sort the list by forecast date in ascending order
        weatherDataList.sortBy { it.timestamp }

        // Return only the required number of days
        return weatherDataList.take(day)
    }

    fun determineCity(cityName: String): City {
        return when (cityName) {
            "CESKE_BUDEJOVICE" -> City.CESKE_BUDEJOVICE
            "NEW_YORK" -> City.NEW_YORK
            "SYDNEY" -> City.SYDNEY
            else -> City.CESKE_BUDEJOVICE // Default to CESKE_BUDEJOVICE if city is not recognized
        }
    }
}