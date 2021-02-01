package org.fireking.basic.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.FragmentViewPagerRecyclerViewOOMBinding

class ViewPagerRecyclerViewOOMFragment : LazyLoadFragment() {

    private var title: String = ""
    private var viewBinding: FragmentViewPagerRecyclerViewOOMBinding? = null

    companion object {
        @JvmStatic
        fun createFragment(title: String): Fragment {
            val fragment = ViewPagerRecyclerViewOOMFragment()
            fragment.arguments = Bundle().apply {
                putString("title", title)
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentViewPagerRecyclerViewOOMBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun lazyLoad() {
        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(requireContext())
        viewBinding?.rvContentList?.adapter = ViewPagerRecyclerViewOOMItemAdapter(title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}