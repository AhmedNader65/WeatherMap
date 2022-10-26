package com.organization.weathermap.data.di

import android.content.Context
import androidx.room.Room
import com.organization.weathermap.data.cache.WeatherDatabase
import com.organization.weathermap.data.cache.Cache
import com.organization.weathermap.data.cache.RoomCache
import com.organization.weathermap.data.cache.daos.WeatherDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): WeatherDatabase {
            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                "weather.db"
            ).fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun provideWeatherDao(
            weatherDatabase: WeatherDatabase
        ): WeatherDao = weatherDatabase.weatherDao()

    }
}