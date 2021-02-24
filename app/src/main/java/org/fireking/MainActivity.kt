package org.fireking

import androidx.compose.ui.window.Dialog
import androidx.fragment.app.Fragment
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityMainBinding
import org.fireking.base.BaseActivity
import org.fireking.basic.BasicFragment
import org.fireking.fight.FightFragment
import org.fireking.library.LibraryFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val fragments = ArrayList<Fragment>()
    private var contentPagerAdapter: MainPagerAdapter? = null

    override fun initView() {
        fragments.add(BasicFragment.createFragment())
        fragments.add(LibraryFragment.createFragment())
        fragments.add(FightFragment.createFragment())
        contentPagerAdapter = MainPagerAdapter(this, fragments)
        bindView {
            contentViewPager.adapter = contentPagerAdapter
            contentViewPager.isUserInputEnabled = false
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.basic -> contentViewPager.currentItem = 0
                    R.id.library -> contentViewPager.currentItem = 1
                    R.id.fight -> contentViewPager.currentItem = 2
                }
                true
            }
        }
    }
}
