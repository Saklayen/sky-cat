package com.gs.skycatnews.ui.newsdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gs.skycatnews.R
import com.gs.skycatnews.base.ui.BaseFragment
import com.gs.skycatnews.base.util.launchAndRepeatWithViewLifecycle
import com.gs.skycatnews.databinding.FragmentStoryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryDetailsFragment :
    BaseFragment<FragmentStoryDetailsBinding>(R.layout.fragment_story_details) {

    private val viewModel: StoryDetailsViewModel by viewModels()
    private val navArgs by navArgs<StoryDetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = viewModel
        launchAndRepeatWithViewLifecycle {
            launch {
                navArgs.let {
                    viewModel.fetchStoryDetails(it.storyId)
                }
            }
        }
    }
}
