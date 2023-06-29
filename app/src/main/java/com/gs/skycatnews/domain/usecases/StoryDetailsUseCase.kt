package com.gs.skycatnews.domain.usecases

import com.gs.skycatnews.domain.FlowUseCase
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.StoryDetails
import com.gs.skycatnews.domain.repositories.StoryDetailsRepository
import com.gs.skycatnews.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StoryDetailsUseCase @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: StoryDetailsRepository
) : FlowUseCase<Int, StoryDetails>(ioDispatcher) {
    override suspend fun execute(parameters: Int): Flow<Result<StoryDetails>> {
        return repository.fetchStoryDetails(parameters)
    }
}
