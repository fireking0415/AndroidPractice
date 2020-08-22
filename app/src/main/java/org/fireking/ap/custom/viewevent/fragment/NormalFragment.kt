package org.fireking.ap.custom.viewevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.normal_fragment_layout.*
import org.fireking.ap.R
import org.fireking.ap.custom.viewevent.adapter.NormalFragmentAdapter

class NormalFragment : Fragment() {

    companion object {

        @JvmStatic
        fun createFragment(): Fragment {
            return NormalFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.normal_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_content_list.layoutManager = LinearLayoutManager(requireContext())
        rv_content_list.adapter = NormalFragmentAdapter()
    }
}