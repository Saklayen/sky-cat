package com.gs.skycatnews.base

interface ScreenSwitcher<S : Screen> {
    fun open(mScreen: S)
    fun goBack()
}
