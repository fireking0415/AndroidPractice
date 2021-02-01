package org.fireking.basic.viewgroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R

class SwipeDeleteAdapter : RecyclerView.Adapter<SwipeDeleteAdapter.SwipeDeleteViewHolder>() {

    private val dataSets = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeDeleteViewHolder {
        return SwipeDeleteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.swipe_delete_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSets.size
    }

    override fun onBindViewHolder(holder: SwipeDeleteViewHolder, position: Int) {

        val item = dataSets[position]
        holder.bind(item)
    }

    fun addNewItemList(list: java.util.ArrayList<String>) {
        dataSets.clear()
        dataSets.addAll(list)
        notifyDataSetChanged()
    }

    class SwipeDeleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            val tvLabel = itemView.findViewById<TextView>(R.id.tvLabel)
            tvLabel.layoutParams = LinearLayout.LayoutParams(
                itemView.resources.displayMetrics.widthPixels,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            tvLabel.text = item
        }

    }
}