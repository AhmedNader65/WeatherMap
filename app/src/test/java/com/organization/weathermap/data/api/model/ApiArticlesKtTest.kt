package com.organization.weathermap.data.api.model

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.organization.weathermap.data.utils.JsonReader
import com.organization.weathermap.domain.model.Article
import com.organization.weathermap.domain.model.Image
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ApiArticlesKtTest {
    lateinit var apiResponse: ApiContainer

    @Before
    fun gettingReady() {
        val gson = GsonBuilder().serializeNulls().create();
        val type = object : TypeToken<ApiContainer>() {}.type

        val articlesRes = JsonReader.getJson("articles.json")
        apiResponse = gson.fromJson(articlesRes, type)!!
    }


    @Test
    fun testMapMediaFromApiToImageDomain() {
        // GIVEN
        val articleToTest = apiResponse.articles.first()
        val expectedValue = Image(
            "https://static01.nyt.com/images/2022/09/12/climate/00cli-patagonia-promo/00cli-patagonia-promo-thumbStandard.jpg",
            "Standard Thumbnail",
            75,
            75
        )
        // WHEN
        val result =
            articleToTest.media?.firstOrNull()?.`media-metadata`?.map { it.mapToDomain() }?.first()

        // THEN
        assertEquals(expectedValue, result)
    }

    @Test
    fun testMapArticleFromApiToArticleDomain() {

        // GIVEN
        val articleToTest = apiResponse.articles.first()
        val expectedValue = Article(
            100000008485792,
            "https://www.nytimes.com/2022/09/14/climate/patagonia-climate-philanthropy-chouinard.html",
            "2022-09-14",
            "2022-09-21 18:05:13",
            "By David Gelles",
            "Billionaire No More: Patagonia Founder Gives Away the Company",
            "Yvon Chouinard has forfeited ownership of the company he founded 49 years ago. The profits will now be used to fight climate change.",
            "",
            articleToTest.media?.firstOrNull()?.`media-metadata`?.map { it.mapToDomain() }.orEmpty()
        )

        // WHEN
        val result =
            articleToTest.mapToDomain()

        // THEN
        assertEquals(expectedValue, result)
    }

}