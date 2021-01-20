package org.fireking.ap.custom.viewevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.custom.viewevent.adapter.NormalFragmentAdapter
import org.fireking.ap.databinding.NormalFragmentLayoutBinding

class NormalFragment : Fragment() {

    private var viewBinding: NormalFragmentLayoutBinding? = null

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
        viewBinding = NormalFragmentLayoutBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(requireContext())
        viewBinding?.rvContentList?.adapter = NormalFragmentAdapter()
    }
}