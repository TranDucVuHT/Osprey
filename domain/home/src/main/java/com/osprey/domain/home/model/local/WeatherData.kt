package com.osprey.domain.home.model.local


data class WeatherData(
    val temperature: String = "-- °C",
    val pm25Quality: String = "--",
    val humidity: String = "--%",
    val pressure: String = "-- hPa",
    val weatherIcon: Int? = null
)