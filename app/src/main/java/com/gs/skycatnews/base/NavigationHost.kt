package com.gs.skycatnews.base

import androidx.appcompat.widget.Toolbar
import com.gs.skycatnews.base.ActivityScreenSwitcher

interface NavigationHost {
    /** Called by MainNavigationFragment to setup it's toolbar with the navigation controller. */
    fun registerToolbarWithNavigation(toolbar: Toolbar)

    fun activityScreenSwitcher(): ActivityScreenSwitcher
}
