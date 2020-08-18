package org.fireking.ap.custom.recyclerview

import androidx.fragment.app.Fragment

abstract class LazyLoadFragment : Fragment() {

    private var isInit = true

    override fun onResume() {
        if (isInit) {
            lazyLoad()
            isInit = false
        }
        super.onResume()
    }

    abstract fun lazyLoad()

}