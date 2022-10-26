package com.organization.nytimes.data.cache


import androidx.room.Database
import androidx.room.RoomDatabase
import com.organization.nytimes.data.cache.daos.WeatherDao
import com.organization.nytimes.data.cache.model.CachedCity
import com.organization.nytimes.data.cache.model.CachedForecast

@Database(
    entities = [
        CachedCity::class, CachedForecast::class
    ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}