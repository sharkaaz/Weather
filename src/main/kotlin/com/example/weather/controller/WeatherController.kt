package com.example.weather.controller

import com.example.weather.model.WeatherDataDto
import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Controller
class WeatherController {

    @PostMapping("/weather")

    fun getWeather (@RequestParam("city") city: String,@RequestParam("cnt") cnt:Int) : String{

        var lat: Double
        var lon: Double

        if (city == "cb") {
            lat = 48.97378881915517
            lon = 14.476120512906416
        } else if (city == "ny") {
            lat = 40.79052384606425
            lon = -73.95908688800822
        } else
        { lat = -33.8470241774331
           lon = 151.0624326592654
        }
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast/daily?lat=${lat}&lon=${lon}&cnt=${cnt}&appid=33aa634c216259f797f35e862f073b40&units=metric"))
            .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val weather  = response.body()

        return "redirect:/"
    }

}