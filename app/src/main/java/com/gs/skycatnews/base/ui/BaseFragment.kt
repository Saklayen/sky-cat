package com.gs.skycatnews.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gs.skycatnews.base.util.autoCleared
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseFragment<T : ViewDataBinding> constructor(@LayoutRes private val mContentLayoutId: Int) :
    Fragment() {

    private var navigationHost: NavigationHost? = null
    var mBinding by autoCleared<T>()
    var mToolbar: Toolbar? = null
        private set


    override fun onAttach(newBase: Context) {
        navigationHost = newBase as NavigationHost
        super.onAttach(ViewPumpContextWrapper.wrap(newBase))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            mContentLayoutId,
            container,
            false
        )
        mBinding.lifecycleOwner = viewLifecycleOwner
        val rootView = mBinding.root
        initToolbar(rootView)

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mToolbar = null

    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
    }


    private fun initToolbar(view: View) {
        if (haveToolbar && resToolbarId != 0) {
            mToolbar = view.findViewById(resToolbarId)
            mToolbar?.apply { navigationHost?.registerToolbarWithNavigation(this) }
        }
    }

    protected open val resToolbarId: Int = 0

    protected open val haveToolbar: Boolean = false

    protected fun activityScreenSwitcher() = navigationHost?.activityScreenSwitcher()

}
