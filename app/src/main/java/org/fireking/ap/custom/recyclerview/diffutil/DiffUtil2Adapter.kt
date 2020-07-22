package org.fireking.ap.custom.recyclerview.diffutil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.nested_sample2_item.*
import org.fireking.ap.R

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/7/22
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 * @author Wanggang
 */
class DiffUtil2Adapter : RecyclerView.Adapter<DiffUtil2Adapter.DiffUtilViewHolder>() {

    private var dataSets = ArrayList<DiffBean>()

    class DiffUtilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
        return DiffUtilViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.nested_sample2_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
    }

    fun submitList(result: MutableList<DiffBean>) {
        submitList2(result)
    }

    private fun submitList2(result: MutableList<DiffBean>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSets[oldItemPosition].id == result[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return dataSets.size
            }

            override fun getNewListSize(): Int {
                return result.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSets[oldItemPosition] == result[newItemPosition]
            }
        }, true)
        dataSets = ArrayList(result)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(
        holder: DiffUtilViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.tv_title.text = dataSets.get(position).title
        Log.e("info", "===================onBindViewHolder")
    }

    override fun getItemCount(): Int {
        return dataSets.size
    }
}