package org.fireking.ap.custom.basic.viewgroup

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.theme_node.view.*
import org.fireking.ap.R

class ThemeNode(context: Context, private val xMindNode: XMindNode) :
    NodeRenderer(context, xMindNode) {

    init {
        LayoutInflater.from(context).inflate(R.layout.theme_node, this, true)
        tv_content.text = xMindNode.nodeName
    }
}