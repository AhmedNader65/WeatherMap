package com.organization.weathermap.data.di

import com.organization.weathermap.data.WeatherRepositoryImp
import com.organization.weathermap.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindWeatherRepository(repository: WeatherRepositoryImp): WeatherRepository

}