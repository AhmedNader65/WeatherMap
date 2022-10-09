package com.organization.nytimes.data.api.model

import com.google.gson.annotations.SerializedName


data class ApiContainer(
    val status: String,
    val copyright: String,
    val num_results: Int,
    @SerializedName("results")  val articles: List<ApiArticles>
)
