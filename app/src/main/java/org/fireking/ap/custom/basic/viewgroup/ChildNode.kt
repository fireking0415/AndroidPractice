package org.fireking.ap.custom.basic.viewgroup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import org.fireking.ap.databinding.ChildNodeBinding

class ChildNode(context: Context, private val xMindNode: XMindNode, private val isLeft: Boolean) :
    NodeRenderer(context, xMindNode) {

    private var viewBinding: ChildNodeBinding? = null

    init {
        viewBinding = ChildNodeBinding.inflate(LayoutInflater.from(context), this, true)
        viewBinding?.tvNodeName?.text = xMindNode.nodeName

        viewBinding?.ivChildLeft?.visibility = View.GONE
        viewBinding?.ivChildRight?.visibility = View.GONE
        if (xMindNode.hasChild) {
            if (isLeft) {
                viewBinding?.ivChildLeft?.visibility = View.VISIBLE
            } else {
                viewBinding?.ivChildRight?.visibility = View.VISIBLE
            }
        } else {
            if (isLeft) {
                viewBinding?.ivChildLeft?.visibility = View.INVISIBLE
            } else {
                viewBinding?.ivChildRight?.visibility = View.INVISIBLE
            }
        }
    }

    fun isLeft(): Boolean {
        return isLeft
    }

}