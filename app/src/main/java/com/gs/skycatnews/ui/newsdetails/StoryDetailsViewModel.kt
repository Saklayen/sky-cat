package com.gs.skycatnews.ui.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.skycatnews.domain.usecases.StoryDetailsUseCase
import com.gs.skycatnews.utils.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class StoryDetailsViewModel @Inject constructor(private val storyDetailsUseCase: StoryDetailsUseCase) :
    ViewModel() {

    private val fetchStoryDetails =
        MutableSharedFlow<Int>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val storyDetails = fetchStoryDetails.flatMapLatest {
        storyDetailsUseCase(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = null
    )

    fun fetchStoryDetails(id: Int) {
        fetchStoryDetails.tryEmit(id)
    }
}
