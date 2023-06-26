package com.gs.skycatnews.ui

import android.os.Bundle
import com.gs.skycatnews.R
import com.gs.skycatnews.base.ui.BaseActivity
import com.gs.skycatnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
