package com.organization.nytimes.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.organization.nytimes.domain.model.City


@Entity(tableName = "city")
data class CachedCity(
    @PrimaryKey
    var cityId: Long = 0L,
    var name: String,
    var country: String,
    var population: Long ,
    var lat: Double,
    var lng: Double,
    var sunrise: Long,
    var sunset: Long
) {
    companion object {
        fun fromDomain(city: City): CachedCity {
            return CachedCity(
                city.id,
                city.name,
                city.country,
                city.population,
                city.lat,
                city.lng,
                city.sunrise,
                city.sunset,
            )
        }

    }
}
