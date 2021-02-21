package org.fireking.fight

import androidx.fragment.app.Fragment
import org.fireking.ap.databinding.NewFightFragmentBinding
import org.fireking.base.BaseFragment

class FightFragment : BaseFragment<NewFightFragmentBinding>() {

    companion object {

        @JvmStatic
        fun createFragment(): Fragment {
            return FightFragment()
        }
    }

    override fun initView() {

    }

}