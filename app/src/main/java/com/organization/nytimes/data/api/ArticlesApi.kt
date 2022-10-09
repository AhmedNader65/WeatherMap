package com.organization.nytimes.data.api

import com.organization.nytimes.BuildConfig
import com.organization.nytimes.data.api.model.ApiArticles
import com.organization.nytimes.data.api.model.ApiContainer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesApi {

    @GET(ApiConstants.MOST_VIEWED_ENDPOINT + "/{section}/{period}.json")
    suspend fun getMostViewed(
        @Path("section") allSections: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String =  BuildConfig.API_KEY
    ): ApiContainer

}