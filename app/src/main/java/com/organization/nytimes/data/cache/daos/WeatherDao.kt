package com.organization.nytimes.data.cache.daos

import androidx.room.*
import com.organization.nytimes.data.cache.model.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao {
    @Transaction
    @Query("SELECT * FROM city where cityId = :id")
    abstract fun getForecast(id: Long): Flow<CachedCityWithForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCity(city: CachedCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertForecast(vararg cachedForecast: CachedForecast)
}