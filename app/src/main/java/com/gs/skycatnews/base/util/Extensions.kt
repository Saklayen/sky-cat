package com.gs.skycatnews.base.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class EmptyClass

fun Fragment.navigate(direction: NavDirections) =
    this.findNavController().navigate(direction)

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

fun FragmentActivity.findNavControllerByFragmentContainerView(@IdRes viewId: Int): NavController {
    val navHostFragment = this.supportFragmentManager.findFragmentById(viewId) as NavHostFragment
    return navHostFragment.navController
}

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

val View.layoutInflater: LayoutInflater
    get() = this.context.layoutInflater
