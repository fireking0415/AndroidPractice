package org.fireking.ap.custom.recyclerview.diffutil

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityDiffUtilBinding
import org.jetbrains.anko.intentFor

class DiffUtilActivity : AppCompatActivity() {

    private lateinit var mDiffUtilAdapter: DiffUtilAdapter
    private var viewBinding: ActivityDiffUtilBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DiffUtilActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDiffUtilBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        val diffCallback = object : DiffUtil.ItemCallback<DiffBean>() {
            override fun areItemsTheSame(oldItem: DiffBean, newItem: DiffBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiffBean, newItem: DiffBean): Boolean {
                return oldItem == newItem
            }
        }

        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(this)
        mDiffUtilAdapter = DiffUtilAdapter(diffCallback)
        viewBinding?.rvContentList?.adapter = mDiffUtilAdapter

        val result2 = ArrayList<DiffBean>()

        result2.add(DiffBean(1, "测试1"))
        result2.add(DiffBean(2, "测试1"))
        result2.add(DiffBean(3, "测试2"))
        result2.add(DiffBean(4, "测试4"))
        result2.add(DiffBean(5, "测试5"))

        mDiffUtilAdapter.submitList(
            result2
        )

        val result3 = ArrayList<DiffBean>()
        result3.add(DiffBean(1, "测试1"))
        result3.add(DiffBean(2, "测试1"))
        result3.add(DiffBean(3, "测试3"))
        result3.add(DiffBean(4, "测试4"))
        result3.add(DiffBean(6, "测试6"))

        viewBinding?.btnChange?.setOnClickListener {
            mDiffUtilAdapter.submitList(result3)
        }

        val result4 = ArrayList<DiffBean>()
        result4.add(DiffBean(1, "测试1"))
        result4.add(DiffBean(2, "测试1"))
        result4.add(DiffBean(3, "测试2"))
        result4.add(DiffBean(4, "测试4444"))
        result4.add(DiffBean(5, "测试5"))
        result4.add(DiffBean(6, "测试666"))
        viewBinding?.btnAdd?.setOnClickListener {
            mDiffUtilAdapter.submitList(result4)
        }
    }
}
