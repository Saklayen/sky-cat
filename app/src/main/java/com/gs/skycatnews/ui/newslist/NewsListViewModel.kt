package com.gs.skycatnews.ui.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.skycatnews.domain.models.Feed
import com.gs.skycatnews.domain.usecases.NewsListUseCase
import com.gs.skycatnews.utils.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewsListViewModel @Inject constructor(private val newsListUseCase: NewsListUseCase) :
    ViewModel() {

    private val fetchNewsFeed =
        MutableSharedFlow<Unit>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val newsFeed = fetchNewsFeed.flatMapLatest {
        newsListUseCase(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = null
    )

    private val _navigate = Channel<NavigationAction>(Channel.CONFLATED)
    val navigate = _navigate.receiveAsFlow()

    init {
        viewModelScope.launch {
            fetchStoryList()
        }
    }

    private fun fetchStoryList() {
        fetchNewsFeed.tryEmit(Unit)
    }

    fun navigate(feed: Feed) {
        when (feed.type) {
            "weblink" -> {
                feed.weblinkUrl?.let {
                    Timber.e("Weblink: $it")
                    _navigate.trySend(NavigationAction.NavigateToWebLink(it))
                }
            }

            "story" -> {
                feed.id?.let {
                    _navigate.trySend(NavigationAction.NavigateToStoryDetails(it))
                }
            }
        }
    }
}

sealed class NavigationAction {
    data class NavigateToStoryDetails(val id: String) : NavigationAction()
    data class NavigateToWebLink(val url: String) : NavigationAction()
}
