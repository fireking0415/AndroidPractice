package org.fireking.ap.custom.recyclerview.diffutil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
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
class DiffUtilAdapter(diffCallback: DiffUtil.ItemCallback<DiffBean>) :
    ListAdapter<DiffBean, DiffUtilAdapter.DiffUtilViewHolder>(diffCallback) {

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

    override fun onBindViewHolder(
        holder: DiffUtilViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.tv_title.text = getItem(position).title
        Log.e("info", "===================onBindViewHolder")
    }
}