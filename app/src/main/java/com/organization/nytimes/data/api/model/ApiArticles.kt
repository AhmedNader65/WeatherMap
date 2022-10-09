package com.organization.nytimes.data.api.model

import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.model.Image


data class ApiArticles(
    val uri: String?,
    val url: String?,
    val id: Long?,
    val asset_id: Long?,
    val source: String?,
    val published_date: String?,
    val updated: String?,
    val subsection: String?,
    val nytdsection: String?,
    val column: String?,
    val byline: String?,
    val type: String?,
    val title: String?,
    val abstract: String?,
    val des_facet: List<String>?,
    val org_facet: List<String>?,
    val per_facet: List<String>?,
    val geo_facet: List<String>?,
    val eta_id: Int?,
    val media: List<ApiMedia>?,
)

data class ApiMedia(
    val type: String?,
    val subtype: String?,
    val caption: String?,
    val copyright: String?,
    val approved_for_syndication: Int?,
    val `media-metadata`: List<ApiMediaMetaData>?,
)

data class ApiMediaMetaData(
    val url: String?,
    val format: String?,
    val height: Int?,
    val width: Int?,
)

fun ApiMediaMetaData.mapToDomain(): Image {
    return Image(
        url = url.orEmpty(),
        format = format.orEmpty(),
        height = height ?: 0,
        width = width ?: 0,
    )
}

fun ApiArticles.mapToDomain(): Article {
    return Article(
        id ?: throw MappingException("Article ID cannot be null"),
        url.orEmpty(),
        published_date.orEmpty(),
        updated.orEmpty(),
        byline.orEmpty(),
        title.orEmpty(),
        abstract.orEmpty(),
        media?.first()?.`media-metadata`?.map { it.mapToDomain() }.orEmpty()
    )
}