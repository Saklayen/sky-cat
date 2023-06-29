package com.gs.skycatnews.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gs.skycatnews.R
import com.gs.skycatnews.base.ui.BaseActivity
import com.gs.skycatnews.base.util.findNavControllerByFragmentContainerView
import com.gs.skycatnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mNavController by lazy { findNavControllerByFragmentContainerView(R.id.nav_host) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.newsListFragment,
        )
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
        toolbar.setupWithNavController(mNavController, appBarConfiguration)
    }
}
