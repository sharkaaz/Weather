package com.example.weather.model

enum class City(val cityName: String, val lat: Double, val lon: Double) {
    BUDEJCE("České Budějovice", 48.97378881915517,
            14.476120512906416),
    NY("New York", 40.79052384606425,
            -73.95908688800822),
    SYD("Sydney", -33.8470241774331,
            151.0624326592654)
}
