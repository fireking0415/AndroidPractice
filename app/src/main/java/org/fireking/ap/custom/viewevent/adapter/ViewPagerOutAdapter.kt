package org.fireking.ap.custom.viewevent.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.fireking.ap.custom.viewevent.fragment.NormalFragment
import org.fireking.ap.custom.viewevent.fragment.ViewPagerFragment

class ViewPagerOutAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        if (position == 1) {
            return ViewPagerFragment()
        } else {
            return NormalFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}