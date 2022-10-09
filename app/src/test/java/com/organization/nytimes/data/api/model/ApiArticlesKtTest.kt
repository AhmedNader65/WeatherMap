package com.organization.nytimes.data.api.model

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.organization.nytimes.data.utils.JsonReader
import com.organization.nytimes.data.utils.JsonReader.getJson
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.model.Image
import com.organization.nytimes.utils.Logger
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiArticlesKtTest {
    lateinit var apiResponse: ApiContainer

    @Before
    fun gettingReady() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter<ApiContainer>(ApiContainer::class.java)
        val articlesRes = JsonReader.getJson("articles.json")
        apiResponse = adapter.fromJson(articlesRes)!!
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
            articleToTest.media?.first()?.`media-metadata`?.map { it.mapToDomain() }?.first()

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
            articleToTest.media?.first()?.`media-metadata`?.map { it.mapToDomain() }.orEmpty()
        )

        // WHEN
        val result =
            articleToTest.mapToDomain()

        // THEN
        assertEquals(expectedValue, result)
    }

}