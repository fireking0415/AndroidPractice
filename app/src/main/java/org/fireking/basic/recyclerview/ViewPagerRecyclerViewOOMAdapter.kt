package org.fireking.basic.recyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerRecyclerViewOOMAdapter(
    private val list: ArrayList<String>,
    private val fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerRecyclerViewOOMFragment.createFragment(list[position])
    }
}