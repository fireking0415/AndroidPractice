package org.fireking.basic.recyclerview.diffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R

class DiffUtilAdapter :
    FixedListAdapter<DiffBean, DiffUtilAdapter.DiffUtilViewHolder>(diffCallback) {

    companion object {
        const val PAY_LOADS_DESC = 1
        private val diffCallback = object : DiffUtil.ItemCallback<DiffBean>() {
            override fun areItemsTheSame(oldItem: DiffBean, newItem: DiffBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiffBean, newItem: DiffBean): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: DiffBean, newItem: DiffBean): Any? {
                if (oldItem.desc != newItem.desc) {
                    return PAY_LOADS_DESC
                }
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }

    class DiffUtilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(diffBean: DiffBean?) {
            itemView.findViewById<TextView>(R.id.tv_title).text = diffBean?.title
            itemView.findViewById<TextView>(R.id.tv_desc).text = diffBean?.desc
        }
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
        holder.bind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: DiffUtilViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            if (payloads[0] == PAY_LOADS_DESC) {
                holder.itemView.findViewById<TextView>(R.id.tv_desc).text =
                    currentList[position].desc
            }
        } else {
            this.onBindViewHolder(holder, position)
        }
    }
}