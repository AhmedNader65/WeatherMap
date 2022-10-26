package com.organization.nytimes.data.di

import com.organization.nytimes.data.WeatherRepositoryImp
import com.organization.nytimes.domain.repository.WeatherRepository
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
    abstract fun bindArticlesRepository(repository: WeatherRepositoryImp): WeatherRepository

}