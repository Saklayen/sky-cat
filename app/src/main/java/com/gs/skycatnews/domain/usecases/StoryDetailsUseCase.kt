package com.gs.skycatnews.domain.usecases

import com.gs.skycatnews.domain.FlowUseCase
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.NewsFeed
import com.gs.skycatnews.domain.repositories.StoryDetailsRepository
import com.gs.skycatnews.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class StoryDetailsUseCase @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: StoryDetailsRepository
) : FlowUseCase<String, NewsFeed>(ioDispatcher) {
    override suspend fun execute(parameters: String): Flow<Result<NewsFeed>> {
        return repository.fetchStory(parameters)
    }
}
