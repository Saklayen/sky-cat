package com.gs.skycatnews.ui.newslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gs.skycatnews.R
import com.gs.skycatnews.base.ui.BaseFragment
import com.gs.skycatnews.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>(R.layout.fragment_news_list) {

    private val viewModel: NewsListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = viewModel
    }
}
