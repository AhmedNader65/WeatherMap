package com.organization.weathermap.data.api.model

import com.organization.weathermap.domain.model.City


data class ApiContainer(
    val cod: String,
    val message: String,
    val cnt: String,
    val list: List<ApiWeatherContainer>,
    val city: ApiCity
)

fun ApiContainer.mapToDomain(): City {
    return City(
        city.id ?: throw MappingException("City ID cannot be null"),
        city.name.orEmpty(),
        city.country.orEmpty(),
        city.population ?: 0,
        city.coord?.lat ?: 0.0,
        city.coord?.lon ?: 0.0,
        city.sunrise ?: 0,
        city.sunset ?: 0,
        list.mapToDomain()
    )
}