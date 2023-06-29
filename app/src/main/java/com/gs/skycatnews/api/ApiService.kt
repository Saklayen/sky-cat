package com.gs.skycatnews.api

import com.gs.skycatnews.domain.models.NewsFeed
import com.gs.skycatnews.domain.models.StoryDetails
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/story/{id}")
    fun fetchStoryDetails(@Path("id") id: Int): Flow<ApiResponse<StoryDetails>>

    @GET("/news-list")
    fun fetchNewsList(): Flow<ApiResponse<NewsFeed>>

}
