package org.fireking.ap.custom.basic.viewgroup

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.child_node.view.*
import org.fireking.ap.R

class ChildNode(context: Context, private val xMindNode: XMindNode, private val isLeft: Boolean) :
    NodeRenderer(context, xMindNode) {

    init {
        LayoutInflater.from(context).inflate(R.layout.child_node, this, true)
        tv_node_name.text = xMindNode.nodeName

        iv_child_left.visibility = View.GONE
        iv_child_right.visibility = View.GONE
        if (xMindNode.hasChild) {
            if (isLeft) {
                iv_child_left.visibility = View.VISIBLE
            } else {
                iv_child_right.visibility = View.VISIBLE
            }
        } else {
            if (isLeft) {
                iv_child_left.visibility = View.INVISIBLE
            } else {
                iv_child_right.visibility = View.INVISIBLE
            }
        }
    }

    fun isLeft(): Boolean {
        return isLeft
    }

}