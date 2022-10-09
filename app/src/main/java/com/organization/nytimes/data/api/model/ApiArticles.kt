package com.organization.nytimes.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiArticles(
    @field:Json(name = "uri") val uri: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "id") val id: Long?,
    @field:Json(name = "asset_id") val asset_id: Long?,
    @field:Json(name = "source") val source: String?,
    @field:Json(name = "published_date") val published_date: String?,
    @field:Json(name = "updated") val updated: String?,
    @field:Json(name = "subsection") val subsection: String?,
    @field:Json(name = "nytdsection") val nytdsection: String?,
    @field:Json(name = "column") val column: String?,
    @field:Json(name = "byline") val byline: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "abstract") val abstract: String?,
    @field:Json(name = "des_facet") val des_facet: List<String>?,
    @field:Json(name = "org_facet") val org_facet: List<String>?,
    @field:Json(name = "per_facet") val per_facet: List<String>?,
    @field:Json(name = "geo_facet") val geo_facet: List<String>?,
    @field:Json(name = "eta_id") val eta_id: Int?,
    @field:Json(name = "media") val media: List<ApiMedia>?,
)

data class ApiMedia(
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "subtype") val subtype: String?,
    @field:Json(name = "caption") val caption: String?,
    @field:Json(name = "copyright") val copyright: String?,
    @field:Json(name = "approved_for_syndication") val approved_for_syndication: Int?,
    @field:Json(name = "media-metadata") val media_metadata: List<ApiMediaMetaData>?,
)

data class ApiMediaMetaData(
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "format") val format: String?,
    @field:Json(name = "height") val height: Int?,
    @field:Json(name = "width") val width: Int?,
)
