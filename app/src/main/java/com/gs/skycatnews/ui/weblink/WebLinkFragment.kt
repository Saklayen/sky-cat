package com.gs.skycatnews.ui.weblink

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gs.skycatnews.R
import com.gs.skycatnews.base.ui.BaseFragment
import com.gs.skycatnews.base.util.launchAndRepeatWithViewLifecycle
import com.gs.skycatnews.databinding.FragmentWebLinkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class WebLinkFragment : BaseFragment<FragmentWebLinkBinding>(R.layout.fragment_web_link) {

    private val viewModel: WebLinkViewModel by viewModels()
    private val navArgs by navArgs<WebLinkFragmentArgs>()
    override val haveToolbar = true
    override val resToolbarId = R.id.toolbar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = viewModel
        launchAndRepeatWithViewLifecycle {
            launch {
                navArgs.let {
                    Timber.e("NavArgs:-------- ${it.url}")
                    viewModel.loadUrl(it.url)
                }
            }
        }
    }
}
