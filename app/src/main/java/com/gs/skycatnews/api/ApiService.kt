package com.gs.skycatnews.api

import com.gs.skycatnews.domain.models.NewsFeed
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/story/{id}")
    fun fetchStory(@Path("id") id: String): Flow<ApiResponse<NewsFeed>>

    @GET("/news-list")
    fun fetchStoryList(): Flow<ApiResponse<NewsFeed>>

}
