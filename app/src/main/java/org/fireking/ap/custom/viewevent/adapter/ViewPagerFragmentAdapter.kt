package org.fireking.ap.custom.viewevent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewpager_inner_item.view.*
import org.fireking.ap.R

class ViewPagerFragmentAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val resultList = ArrayList<MultiItem>()

    init {
        resultList.add(MultiItem(1, null))
        resultList.add(MultiItem(2, null))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ViewPagerInnerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewpager_inner_item, parent, false)
            )
        } else {
            return NormalViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.normal_fragment_item, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return resultList[position].itemType
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView
    }

    inner class ViewPagerInnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {

        fun bind() {
            itemView.apply {
                innerViewPager.adapter = ViewPagerInnerAdapter(fragment.childFragmentManager)
            }
        }

        override val containerView: View?
            get() = itemView
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewPagerInnerViewHolder) {
            holder.bind()
        }
    }

}