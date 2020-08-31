package org.fireking.ap.custom.viewevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.fireking.ap.R

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/8/31
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 * @author Wanggang
 */

class InnerPagerFragment : Fragment() {

    companion object {
        @JvmStatic
        fun createFragment(): Fragment {
            return InnerPagerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inner_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}