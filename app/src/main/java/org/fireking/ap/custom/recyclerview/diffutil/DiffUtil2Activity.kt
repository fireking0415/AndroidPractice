package org.fireking.ap.custom.recyclerview.diffutil

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.ActivityDiffUtil2Binding
import org.fireking.library.kotlin.ext.intentFor

class DiffUtil2Activity : AppCompatActivity() {

    private lateinit var mDiffUtilAdapter: DiffUtil2Adapter

    private var viewBinding: ActivityDiffUtil2Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DiffUtil2Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDiffUtil2Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(this)
        mDiffUtilAdapter = DiffUtil2Adapter()
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
        result4.add(DiffBean(3, "测试3"))
        result4.add(DiffBean(4, "测试4"))
        result4.add(DiffBean(6, "测试6"))
        result4.add(DiffBean(5, "测试55"))
        viewBinding?.btnAdd?.setOnClickListener {
            mDiffUtilAdapter.submitList(result4)
        }
    }
}
