package org.fireking.basic.view

import android.content.Context
import org.fireking.basic.view.widget.MapNode
import org.fireking.ap.databinding.ActivityXMindBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class XMindActivity : BaseActivity<ActivityXMindBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<XMindActivity>())
        }
    }

    override fun initView() {
        bindView {
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
}