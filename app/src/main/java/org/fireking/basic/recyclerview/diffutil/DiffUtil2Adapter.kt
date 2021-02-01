package org.fireking.basic.recyclerview.diffutil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R

class DiffUtil2Adapter : RecyclerView.Adapter<DiffUtil2Adapter.DiffUtilViewHolder>() {

    private var dataSets = ArrayList<DiffBean>()

    class DiffUtilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

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
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = dataSets[position].title
        Log.e("info", "===================onBindViewHolder")
    }

    override fun getItemCount(): Int {
        return dataSets.size
    }
}