package org.fireking.ap.custom.viewevent.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.fireking.ap.custom.viewevent.fragment.NormalFragment

class ViewPagerInnerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return NormalFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}