package org.fireking.ap.custom.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.wannianli_body.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import org.fireking.ap.R


class WanNianLiBodyAdapter : DelegateAdapter.Adapter<WanNianLiBodyAdapter.BodyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder {
        return BodyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.wannianli_body,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return LinearLayoutHelper()
    }

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.bind()
    }

    class BodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        fun bind() {

            val mTitleDataList = ArrayList<String>()

            mTitleDataList.add("测试1")
            mTitleDataList.add("测试2")
            mTitleDataList.add("测试3")
            mTitleDataList.add("测试4")
            mTitleDataList.add("测试5")
            mTitleDataList.add("测试6")
            mTitleDataList.add("测试7")

            val fragmentActivity = itemView.context as FragmentActivity
            viewPager.adapter = WanNianliNewsFragmentAdapter(
                fragmentActivity.supportFragmentManager,
                mTitleDataList
            )

            val navigator = CommonNavigator(itemView.context)
            navigator.adapter = object : CommonNavigatorAdapter() {
                override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                    val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                    colorTransitionPagerTitleView.normalColor = Color.GRAY
                    colorTransitionPagerTitleView.selectedColor = Color.BLACK
                    colorTransitionPagerTitleView.text = mTitleDataList.get(index)
                    colorTransitionPagerTitleView.setOnClickListener {
                        viewPager.currentItem = index
                    }
                    return colorTransitionPagerTitleView
                }

                override fun getCount(): Int {
                    return mTitleDataList.size
                }

                override fun getIndicator(context: Context?): IPagerIndicator {
                    val indicator = LinePagerIndicator(context)
                    indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                    return indicator
                }

            }
            magic_indicator.navigator = navigator
            ViewPagerHelper.bind(magic_indicator, viewPager)
        }

        override val containerView: View?
            get() = itemView

    }
}