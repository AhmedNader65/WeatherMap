package com.organization.nytimes.domain.model

import org.junit.Assert.*

import org.junit.Test

class ArticleTest {
    val thumbnail = Image(
        "https://static01.nyt.com/images/2022/09/30/us/30FORT-MYERS-1-sub/30FORT-MYERS-1-sub-thumbStandard.jpg",
        "Standard Thumbnail",
        75,
        75
    )
    val medium = Image(
        "https://static01.nyt.com/images/2022/09/30/us/30FORT-MYERS-1-sub/30FORT-MYERS-1-sub-thumbStandard.jpg",
        "mediumThreeByTwo210",
        140,
        210
    )

    @Test
    fun getSmallestAvailablePhoto() {
        // Given
        val article = Article(1, "", "", "", "", "", "", listOf(thumbnail, medium))
        val expectedValue = thumbnail.url
        // When
        val smallestPhoto = article.getSmallestImage()
        // Then
        assertEquals(expectedValue,smallestPhoto)
    }

    @Test
    fun getSmallestAvailablePhoto_noSmallPhoto_returnsMedium() {
        // Given

        val article = Article(1, "", "", "", "", "", "", listOf(medium))
        val expectedValue = medium.url
//        // When
        val smallestPhoto = article.getSmallestImage()
//        // Then
        assertEquals(expectedValue, smallestPhoto)
    }
}