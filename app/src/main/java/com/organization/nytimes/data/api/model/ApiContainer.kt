package com.organization.nytimes.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiContainer(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "copyright") val copyright: String,
    @field:Json(name = "num_results") val num_results: Int,
    @field:Json(name = "results") val articles: List<ApiArticles>
)
