package org.fireking.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.databinding.NewBasicFragmentItemBinding
import java.util.*

class BasicAdapter(private val block: (entity: BasicEntity) -> Unit) :
    RecyclerView.Adapter<BasicAdapter.BasicViewHolder>() {

    private val dataList = ArrayList<BasicEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        return BasicViewHolder(
            NewBasicFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), block
        )
    }

    fun submitList(result: ArrayList<BasicEntity>) {
        dataList.clear()
        dataList.addAll(result)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class BasicViewHolder(
        private val viewBinding: NewBasicFragmentItemBinding,
        private val block: (clazz: BasicEntity) -> Unit
    ) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(basicEntity: BasicEntity) {
            viewBinding.tvRvItem.text = basicEntity.title
            viewBinding.tvRvItem.setOnClickListener {
                block(basicEntity)
            }
        }

    }

    data class BasicEntity(
        var title: String,
        var clazz: Class<out AppCompatActivity>?
    )
}