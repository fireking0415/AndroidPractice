package org.fireking.basic.view.widget

import android.content.Context
import android.view.LayoutInflater
import org.fireking.ap.databinding.ThemeNodeBinding

class ThemeNode(context: Context, private val xMindNode: XMindNode) :
    NodeRenderer(context, xMindNode) {

    private var viewBinding: ThemeNodeBinding? = null

    init {
        viewBinding = ThemeNodeBinding.inflate(LayoutInflater.from(context), this, true)
        viewBinding?.tvContent?.text = xMindNode.nodeName
    }
}