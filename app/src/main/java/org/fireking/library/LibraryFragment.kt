package org.fireking.library

import androidx.fragment.app.Fragment
import org.fireking.ap.databinding.NewLibraryFragmentBinding
import org.fireking.base.BaseFragment

class LibraryFragment : BaseFragment<NewLibraryFragmentBinding>() {

    companion object {
        @JvmStatic
        fun createFragment(): Fragment {
            return LibraryFragment()
        }
    }

    override fun initView() {

    }
}