package com.gs.skycatnews.domain.usecases

import com.gs.skycatnews.domain.FlowUseCase
import com.gs.skycatnews.domain.Result
import com.gs.skycatnews.domain.models.NewsFeed
import com.gs.skycatnews.domain.repositories.NewsListRepository
import com.gs.skycatnews.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsListUseCase @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: NewsListRepository
) : FlowUseCase<Unit, NewsFeed>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<Result<NewsFeed>> {
        return repository.fetchStoryList()
    }
}
