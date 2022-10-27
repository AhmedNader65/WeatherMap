package com.organization.weathermap.data.cache


import androidx.room.Database
import androidx.room.RoomDatabase
import com.organization.weathermap.data.cache.daos.WeatherDao
import com.organization.weathermap.data.cache.model.CachedCity
import com.organization.weathermap.data.cache.model.CachedForecast

@Database(
    entities = [
        CachedCity::class, CachedForecast::class
    ],
    version = 3
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}