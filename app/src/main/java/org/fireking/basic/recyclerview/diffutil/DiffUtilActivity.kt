package org.fireking.basic.recyclerview.diffutil

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.esotericsoftware.kryo.Kryo
import org.fireking.ap.databinding.ActivityDiffUtilBinding
import org.fireking.library.kotlin.ext.intentFor

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

        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(this)
        mDiffUtilAdapter = DiffUtilAdapter()
        viewBinding?.rvContentList?.adapter = mDiffUtilAdapter

        val result = ArrayList<DiffBean>()

        result.add(DiffBean(1, "测试1", "测试内容描述1"))
        result.add(DiffBean(2, "测试2", "测试内容描述2"))
        result.add(DiffBean(3, "测试3", "测试内容描述3"))
        result.add(DiffBean(4, "测试4", "测试内容描述4"))
        result.add(DiffBean(5, "测试5", "测试内容描述5"))

        mDiffUtilAdapter.submitList(result)

        viewBinding?.btnAdd?.setOnClickListener {
            result.add(DiffBean(6, "测试6", "测试内容描述6"))
            mDiffUtilAdapter.submitList(result)
        }
        viewBinding?.btnRemove?.setOnClickListener {
            result.removeAt(3)
            mDiffUtilAdapter.submitList(result)
        }
        viewBinding?.btnUpdate?.setOnClickListener {
            result[3].desc = "测试内容描述变更3"
            mDiffUtilAdapter.submitList(result)
        }
    }
}
