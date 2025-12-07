//package com.osprey.data.home.repository
//
//
//import android.location.Location
//import com.osprey.data.home.datasource.remote.api.WeatherApiService
//import com.osprey.domain.home.model.local.WeatherData
//import javax.inject.Inject
//
//class WeatherRepositoryImpl @Inject constructor(
//    private val weatherApi: WeatherApiService) {
//    suspend fun getWeatherData(location: Location, apiKey: String): Result<WeatherData> {
//        return try {
//            val weather = weatherApi.getCurrentWeather(
//                latitude = location.latitude,
//                longitude = location.longitude,
//                apiKey = apiKey
//            )
//
//            val airQuality = try {
//                weatherApi.getAirQuality(
//                    latitude = location.latitude,
//                    longitude = location.longitude,
//                    apiKey = apiKey
//                )
//            } catch (e: Exception) {
//                null
//            }
//
//            val pm25 = airQuality?.list?.firstOrNull()?.components?.pm2_5 ?: 0.0
//            val quality = when {
//                pm25 <= 12 -> "Good"
//                pm25 <= 35.4 -> "Moderate"
//                pm25 <= 55.4 -> "Unhealthy"
//                pm25 <= 150.4 -> "Poor"
//                pm25 <= 250.4 -> "Very Poor"
//                else -> "Hazardous"
//            }
//
//            Result.success(WeatherData(
//                temperature = "${weather.main.temp.toInt()}°C",
//                pm25Quality = quality,
//                humidity = "${weather.main.humidity}%",
//                pressure = "${weather.main.pressure} hPa"
//            ))
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}