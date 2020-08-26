package org.fireking.ap.custom.viewevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.viewpager_fragment.*
import org.fireking.ap.R
import org.fireking.ap.custom.viewevent.adapter.ViewPagerFragmentAdapter

class ViewPagerFragment : Fragment() {

    companion object {
        @JvmStatic
        fun createFragment(): Fragment {
            return ViewPagerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewpager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_content_list.layoutManager = LinearLayoutManager(requireContext())
        rv_content_list.adapter = ViewPagerFragmentAdapter(this)
    }
}