package org.fireking.ap.custom.coordinatorLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CoorDemo1Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return BlankFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}