package com.osprey.home.ui.home


import com.osprey.domain.home.model.local.WeatherData

data class HomeUiState(
    val weatherData: WeatherData = WeatherData(),
    val isLoadingWeather: Boolean = false,
    val weatherError: String? = null
)
