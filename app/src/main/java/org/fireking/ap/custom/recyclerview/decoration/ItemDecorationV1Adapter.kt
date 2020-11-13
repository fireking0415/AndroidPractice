package org.fireking.ap.custom.recyclerview.decoration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_list_item2.*
import org.fireking.ap.R

class ItemDecorationV1Adapter :
    RecyclerView.Adapter<ItemDecorationV1Adapter.ItemDecorationV1ViewHolder>() {

    class ItemDecorationV1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        fun bind() {
            tv_text.text = "当前是$adapterPosition"
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "点击了单个ItemView${adapterPosition}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override val containerView: View?
            get() = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDecorationV1ViewHolder {
        return ItemDecorationV1ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ItemDecorationV1ViewHolder, position: Int) {
        holder.bind()
    }
}