package org.fireking.basic

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.NewBasicFragmentBinding
import org.fireking.base.BaseFragment
import org.fireking.basic.animator.AnimActivity
import org.fireking.basic.image.ImageActivity
import org.fireking.basic.nested.NestedActivity
import org.fireking.basic.recyclerview.RecyclerViewSampleActivity
import org.fireking.basic.textview.TextViewModuleActivity
import org.fireking.basic.view.ViewModuleActivity
import org.fireking.basic.viewgroup.CustomViewGroupActivity
import org.fireking.library.mpandroidchart.MPChartActivity

class BasicFragment : BaseFragment<NewBasicFragmentBinding>() {

    private var mBasicAdapter: BasicAdapter? = null

    companion object {
        @JvmStatic
        fun createFragment(): Fragment {
            return BasicFragment()
        }
    }

    override fun initView() {
        mBasicAdapter = BasicAdapter { entity ->
            entity.let {
                if (it.clazz != null) {
                    requireActivity().startActivity(Intent(requireActivity(), it.clazz).apply {
                        putExtra("title", it.title)
                    })
                }
            }
        }
        bindView {
            rvBasicList.layoutManager = LinearLayoutManager(requireActivity())
            rvBasicList.adapter = mBasicAdapter
        }
    }

    override fun loadData() {
        mBasicAdapter?.submitList(
            arrayListOf(
                BasicAdapter.BasicEntity("View&Canvas", ViewModuleActivity::class.java),
                BasicAdapter.BasicEntity("TextView", TextViewModuleActivity::class.java),
                BasicAdapter.BasicEntity("Animator", AnimActivity::class.java),
                BasicAdapter.BasicEntity("ImageView", ImageActivity::class.java),
                BasicAdapter.BasicEntity("ViewGroup", CustomViewGroupActivity::class.java),
                BasicAdapter.BasicEntity("RecyclerView", RecyclerViewSampleActivity::class.java),
                BasicAdapter.BasicEntity("NestedScrolling", NestedActivity::class.java),
                BasicAdapter.BasicEntity("MPAndroidChart", MPChartActivity::class.java),
                BasicAdapter.BasicEntity("Thread", null),
                BasicAdapter.BasicEntity("EditText&Keyboard", null),
                BasicAdapter.BasicEntity("Notification", null),
                BasicAdapter.BasicEntity("MediaPlayer", null),
                BasicAdapter.BasicEntity("Service", null),
                BasicAdapter.BasicEntity("Aidl", null),
            )
        )
    }
}