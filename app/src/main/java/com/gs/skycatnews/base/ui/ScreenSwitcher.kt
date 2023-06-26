package com.gs.skycatnews.base.ui

interface ScreenSwitcher<S : Screen> {
    fun open(mScreen: S)
    fun goBack()
}
