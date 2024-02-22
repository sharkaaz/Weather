package com.example.weather.service

import com.example.weather.model.WeatherData
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDate
import org.slf4j.LoggerFactory
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Service
class WeatherService {
    private val objectMapper = ObjectMapper()
    private val formatter = DateTimeFormatter.ofPattern("EEE dd/MM")

    fun parseWeatherData(response: String?, day: Int): List<WeatherData>? {
        if (response.isNullOrBlank()) return null

        val rootNode = objectMapper.readTree(response) ?: return null
        val forecastsNode = rootNode["list"] ?: return null

        val weatherDataList = mutableListOf<WeatherData>()
        val today = LocalDate.now()

        for (forecastNode in forecastsNode) {
            val forecastDate = LocalDate.ofEpochDay(forecastNode["dt"].asLong())
            if (forecastDate.isEqual(today) || forecastDate.isAfter(today)) {
                val temperature = forecastNode["temp"]["day"].asDouble().toFloat() // Temperature is a float
                val formattedDate = forecastDate.format(DateTimeFormatter.ofPattern("EEE dd/MM"))
                weatherDataList.add(WeatherData(temperature, forecastDate.toEpochDay(), formattedDate))
            }
        }
        return weatherDataList
    }
}