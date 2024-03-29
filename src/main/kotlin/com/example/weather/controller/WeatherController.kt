package com.example.weather.controller

import com.example.weather.model.City
import com.example.weather.service.WeatherService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@Controller
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/weather")
    fun getWeather(
            @RequestParam city: String,
            @RequestParam day: Int,
            model: Model
    ): String {
        val cityEn = weatherService.determineCity(city)

        val apiKey = "33aa634c216259f797f35e862f073b40"
        val url = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=${cityEn.lat}&lon=${cityEn.lon}&cnt=${day}&appid=${apiKey}&units=metric"

        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject(url, String::class.java)

        val temperatures = weatherService.parseWeatherData(response, day, cityEn)

        model.addAttribute("day",day)
        model.addAttribute("city", city)
        model.addAttribute("temperatures", temperatures)

        return "weather"
    }
}