package com.organization.weathermap.data.api.model

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.organization.weathermap.data.utils.JsonReader
import com.organization.weathermap.domain.model.City

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ApiWeatherTest {
    lateinit var apiResponse: ApiContainer

    @Before
    fun gettingReady() {
        val gson = GsonBuilder().serializeNulls().create();
        val type = object : TypeToken<ApiContainer>() {}.type

        val json = JsonReader.getJson("forecast.json")
        apiResponse = gson.fromJson(json, type)!!
    }


    @Test
    fun testMapCityFromApiToDomain() {
        // GIVEN
        val expectedValue = City(
            360630, "Cairo", "EG", 7734614, 30.0626, 31.2497, 1666843492, 1666883561, listOf()
        )
        // WHEN
        val result = apiResponse.mapToDomain()

        // THEN
        assertEquals(expectedValue.id, result.id)
        assertEquals(expectedValue.name, result.name)
        assertEquals(expectedValue.population, result.population)
        assert(expectedValue.lat == result.lat)
        assert(expectedValue.lng == result.lng)
    }


}