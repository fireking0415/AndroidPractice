package org.fireking.ap.custom.basic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.basic.viewgroup.MapNode
import org.fireking.ap.databinding.ActivityXMindBinding
import org.fireking.library.kotlin.ext.intentFor

class XMindActivity : AppCompatActivity() {

    private var viewBinding: ActivityXMindBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<XMindActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityXMindBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
        viewBinding?.xMindView?.post {
            viewBinding?.xMindView?.setData(
                arrayListOf(
                    MapNode(isTheme = true, hasSubNode = true, nodeName = "供应商", level = 1),
                    MapNode(hasSubNode = true, nodeName = "上海交通大学", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海海洋大学", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海工程技术大学", level = 2),
                    MapNode(hasSubNode = false, nodeName = "上海中医药大学", level = 2),
                    MapNode(hasSubNode = false, nodeName = "上海中医药大学", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海第二工业大学", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海第二工业大学", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海应用技术学院", level = 2),
                    MapNode(hasSubNode = true, nodeName = "上海出版印刷高等专科学校", level = 2)
                )
            )
        }
    }
}