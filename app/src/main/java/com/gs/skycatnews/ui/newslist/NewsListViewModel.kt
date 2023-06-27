package com.gs.skycatnews.ui.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.skycatnews.domain.Status
import com.gs.skycatnews.domain.usecases.StoryListUseCase
import com.gs.skycatnews.utils.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewsListViewModel @Inject constructor(private val storyListUseCase: StoryListUseCase) :
    ViewModel() {

    private val fetchNewsFeed =
        MutableSharedFlow<Unit>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val newsFeed = fetchNewsFeed.flatMapLatest {
        storyListUseCase(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = null
    )

    var firstImageUrl = ""
    var firstHeadline = ""

    init {
        viewModelScope.launch {
            fetchStoryList()
        }

        viewModelScope.launch {
            newsFeed.collect {
                if (it?.status == Status.SUCCESS){
                    firstImageUrl = it.data?.feeds?.get(0)?.teaserImage.toString()
                    firstHeadline = it.data?.feeds?.get(0)?.headline.toString()
                    Timber.e("storyList.firstHeadline: $firstHeadline")
                }
            }
        }
    }

    private fun fetchStoryList() {
        fetchNewsFeed.tryEmit(Unit)
    }
}
