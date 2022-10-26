package com.organization.nytimes.data.api.model

import com.organization.nytimes.domain.model.Forecast


data class ApiWeatherContainer(
    val dt: Long?,
    val main: ApiMain?,
    val weather: List<ApiWeather>?,
    val clouds: ApiCloud?,
    val wind: ApiWind?,
    val visibility: Int?,
    val pop: Int?,
    val dt_txt: String?,
)

fun List<ApiWeatherContainer>.mapToDomain(): List<Forecast> {
    return map {
        Forecast(
            it.dt ?: System.currentTimeMillis(),
            it.dt_txt.orEmpty(),
            it.main?.temp.orEmpty(),
            it.main?.feels_like.orEmpty(),
            it.main?.temp_min.orEmpty(),
            it.main?.temp_max.orEmpty(),
            it.main?.pressure.orEmpty(),
            it.main?.sea_level.orEmpty(),
            it.main?.grnd_level.orEmpty(),
            it.main?.humidity.orEmpty(),
            it.main?.temp_kf.orEmpty(),
            it.weather?.first()?.description.orEmpty(),
            it.clouds?.all.orEmpty(),
            it.wind?.speed.orEmpty(),
            it.visibility ?: 0
        )
    }
}

data class ApiMain(
    val temp: String?,
    val feels_like: String?,
    val temp_min: String?,
    val temp_max: String?,
    val pressure: String?,
    val sea_level: String?,
    val grnd_level: String?,
    val humidity: String?,
    val temp_kf: String?,
)

data class ApiWeather(
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?,
)

data class ApiCloud(
    val all: String?,
)

data class ApiWind(
    val speed: String?,
    val deg: String?,
    val gust: String?,
)
