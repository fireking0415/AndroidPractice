package org.fireking

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
//            coordinatorLayout.setOnClickListener {
//                CoordinatorLayoutActivity.start(this@MainActivity)
//            }
//
//            btnCanvas.setOnClickListener {
//                CanvasBasicActivity.startActivity(this@MainActivity)
//            }
//
//            btnPath.setOnClickListener {
//                BasicPathActivity.startActivity(this@MainActivity)
//            }
//
//            btnFlip.setOnClickListener {
//                BasicFlipActivity.startActivity(this@MainActivity)
//            }
//
//            btnNotification.setOnClickListener {
//                NotificationActivity.start(this@MainActivity)
//            }
//
//            btnBezier.setOnClickListener {
//                BasicBezierActivity.startActivity(this@MainActivity)
//            }
//
//            btnViewGroup.setOnClickListener {
//                CustomViewGroupActivity.startActivity(this@MainActivity)
//            }
//
//            btnNested.setOnClickListener {
//                NestedActivity.start(this@MainActivity)
//            }
//
//            btnRecyclerView.setOnClickListener {
//                RecyclerViewSampleActivity.start(this@MainActivity)
//            }
//
//            btnWindowManager.setOnClickListener {
//                WindowManagerSampleActivity.start(this@MainActivity)
//            }
//
//            btnConstraintLayout.setOnClickListener {
//                ConstraintLayoutSample.start(this@MainActivity)
//            }
//
//            btnAnim.setOnClickListener {
//                AnimActivity.start(this@MainActivity)
//            }
//
//            btnNewFunction.setOnClickListener {
//                NewFunctionActivity.start(this@MainActivity)
//            }
//
//            btnThread.setOnClickListener {
//                ThreadActivity.start(this@MainActivity)
//            }
//
//            btnViewEvent.setOnClickListener {
//                ViewEventSampleActivity.start(this@MainActivity)
//            }
//
//            btnRestudy.setOnClickListener {
//                RestudyActivity.start(this@MainActivity)
//            }
//
//            btnTestImage.setOnClickListener {
//                ImageActivity.start(this@MainActivity)
//            }
//
//            btnLiveDataViewModel.setOnClickListener {
//                LiveDataViewModelActivity.start(this@MainActivity)
//            }
//
//            btnTestTextView.setOnClickListener {
//                TestTextViewActivity.start(this@MainActivity)
//            }
//
//            btnMPLineChart.setOnClickListener {
//                MPChartActivity.start(this@MainActivity)
//            }
//
//            btnTestARouter.setOnClickListener {
//                ARouterSampleActivity.start(this@MainActivity)
//            }
        }
    }

}
