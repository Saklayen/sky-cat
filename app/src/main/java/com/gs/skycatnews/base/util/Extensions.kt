package com.gs.skycatnews.base.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class EmptyClass

fun Fragment.navigateUp() = this.findNavController().navigateUp()
fun Activity.navigate(@IdRes viewId: Int, direction: NavDirections) =
    this.findNavController(viewId).navigate(direction)

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