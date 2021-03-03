package org.fireking.basic.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class FixedListAdapter<T, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {

    override fun submitList(list: MutableList<T>?) {
        val deepCopyResult = list?.map { it!!.deepCopy() }?.toList()
        super.submitList(deepCopyResult)
    }

    override fun submitList(list: MutableList<T>?, commitCallback: Runnable?) {
        val deepCopyResult = list?.map { it!!.deepCopy() }?.toList()
        super.submitList(deepCopyResult, commitCallback)
    }
}