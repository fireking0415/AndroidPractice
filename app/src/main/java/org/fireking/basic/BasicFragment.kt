package org.fireking.basic

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.NewBasicFragmentBinding
import org.fireking.base.BaseFragment
import org.fireking.basic.animator.AnimActivity
import org.fireking.basic.textview.TextViewModuleActivity
import org.fireking.basic.view.ViewModuleActivity

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
                BasicAdapter.BasicEntity("EditText&Keyboard", null),
                BasicAdapter.BasicEntity("ViewGroup", null),
                BasicAdapter.BasicEntity("Thread", null),
                BasicAdapter.BasicEntity("Notification", null),
                BasicAdapter.BasicEntity("MediaPlayer", null),
                BasicAdapter.BasicEntity("Service", null),
                BasicAdapter.BasicEntity("Aidl", null),
                BasicAdapter.BasicEntity("RecyclerView", null),
            )
        )
    }
}