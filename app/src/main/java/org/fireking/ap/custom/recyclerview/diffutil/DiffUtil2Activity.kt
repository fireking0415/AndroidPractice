package org.fireking.ap.custom.recyclerview.diffutil

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_diff_util.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class DiffUtil2Activity : AppCompatActivity() {

    private lateinit var mDiffUtilAdapter: DiffUtil2Adapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DiffUtil2Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util2)

        rv_content_list.layoutManager = LinearLayoutManager(this)
        mDiffUtilAdapter = DiffUtil2Adapter()
        rv_content_list.adapter = mDiffUtilAdapter

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

        btnChange.setOnClickListener {
            mDiffUtilAdapter.submitList(result3)
        }

        val result4 = ArrayList<DiffBean>()
        result4.add(DiffBean(1, "测试1"))
        result4.add(DiffBean(2, "测试1"))
        result4.add(DiffBean(3, "测试3"))
        result4.add(DiffBean(4, "测试4"))
        result4.add(DiffBean(6, "测试6"))
        result4.add(DiffBean(5, "测试55"))
        btnAdd.setOnClickListener {
            mDiffUtilAdapter.submitList(result4)
        }
    }
}
