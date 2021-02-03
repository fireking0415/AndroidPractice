package org.fireking.library

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.NewLibraryFragmentBinding
import org.fireking.base.BaseFragment
import org.fireking.basic.BasicAdapter
import org.fireking.library.kotlin.KotlinModuleActivity

class LibraryFragment : BaseFragment<NewLibraryFragmentBinding>() {

    private var mBasicAdapter: BasicAdapter? = null

    companion object {
        @JvmStatic
        fun createFragment(): Fragment {
            return LibraryFragment()
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
                BasicAdapter.BasicEntity("Kotlin", KotlinModuleActivity::class.java),
            )
        )
    }
}