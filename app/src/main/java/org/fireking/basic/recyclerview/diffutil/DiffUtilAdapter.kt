package org.fireking.basic.recyclerview.diffutil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R

class DiffUtilAdapter(diffCallback: DiffUtil.ItemCallback<DiffBean>) :
    ListAdapter<DiffBean, DiffUtilAdapter.DiffUtilViewHolder>(diffCallback) {

    class DiffUtilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = getItem(position).title
        Log.e("info", "===================onBindViewHolder")
    }
}