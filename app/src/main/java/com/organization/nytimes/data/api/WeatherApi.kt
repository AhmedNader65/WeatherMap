package com.organization.nytimes.data.api

import com.organization.nytimes.BuildConfig
import com.organization.nytimes.data.api.model.ApiContainer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiConstants.FORECAST_ENDPOINT)
    suspend fun getForecast(
        @Query(ApiParameters.API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(ApiParameters.CITY_QUERY) city: String
    ): ApiContainer

}