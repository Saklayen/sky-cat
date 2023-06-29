package com.gs.skycatnews.domain.repositories

import com.gs.skycatnews.api.ApiService
import com.gs.skycatnews.api.NetworkResource
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.StoryDetails
import com.gs.skycatnews.hilt.IoDispatcher
import com.gs.skycatnews.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryDetailsRepository @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    val apiService: ApiService
) {
    private val controlledRunner = ControlledRunner<Flow<Result<StoryDetails>>>()

    suspend fun fetchStoryDetails(id: Int): Flow<Result<StoryDetails>> {
        return controlledRunner.cancelPreviousThenRun {
            object : NetworkResource<StoryDetails>(dispatcher) {
                override suspend fun createCall() = apiService.fetchStoryDetails(id)
            }.asFlow()
        }
    }
}
