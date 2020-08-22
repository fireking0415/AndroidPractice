package org.fireking.ap.custom.viewevent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import org.fireking.ap.R

class NormalFragmentAdapter :
    RecyclerView.Adapter<NormalFragmentAdapter.NormalFragmentViewHolder>() {

    inner class NormalFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalFragmentViewHolder {
        return NormalFragmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.normal_fragment_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: NormalFragmentViewHolder, position: Int) {

    }
}