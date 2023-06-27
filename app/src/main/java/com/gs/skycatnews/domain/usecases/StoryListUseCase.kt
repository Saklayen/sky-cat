package com.gs.skycatnews.domain.usecases

import com.gs.skycatnews.domain.FlowUseCase
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.NewsFeed
import com.gs.skycatnews.domain.repositories.StoryListRepository
import com.gs.skycatnews.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class StoryListUseCase @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: StoryListRepository
) : FlowUseCase<Unit, NewsFeed>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<Result<NewsFeed>> {
        return repository.fetchStoryList()
    }
}
