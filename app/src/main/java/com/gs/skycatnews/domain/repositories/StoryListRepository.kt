package com.gs.skycatnews.domain.repositories

import com.gs.skycatnews.api.ApiService
import com.gs.skycatnews.api.NetworkResource
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.NewsFeed
import com.gs.skycatnews.hilt.IoDispatcher
import com.gs.skycatnews.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryListRepository @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    val apiService: ApiService
) {
    private val controlledRunner = ControlledRunner<Flow<Result<NewsFeed>>>()

    suspend fun fetchStoryList(): Flow<Result<NewsFeed>> {
        return controlledRunner.joinPreviousOrRun {
            object : NetworkResource<NewsFeed>(dispatcher) {
                override suspend fun createCall() = apiService.fetchStoryList()
            }.asFlow()
        }
    }
}
