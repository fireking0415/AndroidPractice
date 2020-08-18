package org.fireking.ap.custom.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_view_pager_recycler_view_o_o_m.*
import org.fireking.ap.R

class ViewPagerRecyclerViewOOMFragment : LazyLoadFragment() {

    private var title: String = ""

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
        return inflater.inflate(R.layout.fragment_view_pager_recycler_view_o_o_m, container, false)
    }

    override fun lazyLoad() {
        rv_content_list.layoutManager = LinearLayoutManager(requireContext())
        rv_content_list.adapter = ViewPagerRecyclerViewOOMItemAdapter(title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}