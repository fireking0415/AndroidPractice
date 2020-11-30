package org.fireking.ap.custom.basic

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_x_mind.*
import org.fireking.ap.R
import org.fireking.ap.custom.basic.viewgroup.MapNode
import org.jetbrains.anko.intentFor

class XMindActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<XMindActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_x_mind)
        xMindView.post {
            xMindView.setData(
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