package com.organization.weathermap.data.api

import com.organization.weathermap.BuildConfig
import com.organization.weathermap.data.api.model.ApiContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiConstants.FORECAST_ENDPOINT)
    suspend fun getForecast(
        @Query(ApiParameters.API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(ApiParameters.CITY_QUERY) city: String
    ): ApiContainer

}