package org.fireking.ap.custom.basic.viewgroup

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import org.fireking.ap.R

class XMindView : View {

    private var themeNode: ThemeNode? = null
    private var childNodeList = ArrayList<ChildNode>()

    private var viewWidth = 0
    private var viewHeight = 0

    private lateinit var themeSize: XMindNodeSize
    private lateinit var childNodeSize: XMindNodeSize

    private val themeRectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val childRectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val lineRectPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var nodeTopBottomMargin: Int = 0
    private var nodeLeftRightMargin: Int = 0

    private val linePath = Path()

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initView(context, attributes)
    }

    private fun initView(context: Context, attributes: AttributeSet?) {
        themeRectPaint.color = Color.parseColor("#1777FF")
        themeRectPaint.style = Paint.Style.FILL

        childRectPaint.color = Color.parseColor("#A9D8FF")
        childRectPaint.style = Paint.Style.FILL

        lineRectPaint.color = Color.parseColor("#66BAFF")
        lineRectPaint.style = Paint.Style.STROKE
        lineRectPaint.strokeWidth = dip(2).toFloat()

        nodeTopBottomMargin = dip(8)
        nodeLeftRightMargin = dip(24)
    }

    private fun dip(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
        initNodeSize()
    }

    private fun initNodeSize() {
        val themeLayout = LayoutInflater.from(context).inflate(R.layout.theme_node, null)
        themeLayout.measure(0, 0)
        themeSize = XMindNodeSize(themeLayout.measuredWidth, themeLayout.measuredHeight)
        val childLayout = LayoutInflater.from(context).inflate(R.layout.child_node, null)
        childLayout.measure(0, 0)
        childNodeSize = XMindNodeSize(childLayout.measuredWidth, childLayout.measuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        themeNode?.let { theme ->

            // 绘制子节点
            childNodeList.forEachIndexed { _, xMindNode ->
                xMindNode.doRender(canvas)
                drawLinkLine(canvas, theme, xMindNode)
            }

            // 绘制主题节点
            theme.doRender(canvas)
        }
    }

    private fun drawLinkLine(canvas: Canvas?, theme: ThemeNode, child: ChildNode) {
        linePath.reset()
        if (child.isLeft()) {
            linePath.moveTo(child.getRightX(), child.getRightY())
            val lineMaxLength = theme.getLeftY() - child.getRightY()
            linePath.cubicTo(
                child.getRightX(),
                child.getRightY(),
                child.getRightX() + nodeLeftRightMargin / 2,
                child.getRightY() + lineMaxLength / 10,
                child.getRightX() + nodeLeftRightMargin / 2,
                child.getRightY() + lineMaxLength / 10 * 3
            )
            linePath.cubicTo(
                theme.getLeftX() - nodeLeftRightMargin / 2,
                theme.getLeftY() - lineMaxLength / 10 * 3,
                theme.getLeftX() - nodeLeftRightMargin / 2,
                theme.getLeftY() - lineMaxLength / 10,
                theme.getLeftX(),
                theme.getLeftY()
            )
        } else {
            linePath.moveTo(child.getLeftX(), child.getLeftY())
            val lineMaxLength = theme.getRightY() - child.getRightY()
            linePath.cubicTo(
                child.getLeftX(),
                child.getLeftY(),
                child.getLeftX() - nodeLeftRightMargin / 2,
                child.getLeftY() + lineMaxLength / 10,
                child.getLeftX() - nodeLeftRightMargin / 2,
                child.getLeftY() + lineMaxLength / 10 * 3
            )
            linePath.cubicTo(
                theme.getRightX() + nodeLeftRightMargin / 2,
                theme.getRightY() - lineMaxLength / 10 * 3,
                theme.getRightX() + nodeLeftRightMargin / 2,
                theme.getRightY() - lineMaxLength / 10,
                theme.getRightX(),
                theme.getRightY()
            )
        }
        canvas?.drawPath(linePath, lineRectPaint)
    }

    fun setData(nodeList: ArrayList<MapNode>) {
        nodeList.find { it.isTheme }?.let { calculationThemeNode(it.nodeName, it.hasSubNode) }
        val leftChildNode = ArrayList<MapNode>()
        val rightChildNode = ArrayList<MapNode>()
        nodeList.filterNot { it.isTheme }.forEachIndexed { index, mapNode ->
            if (index % 2 == 0) {
                leftChildNode.add(mapNode)
            } else {
                rightChildNode.add(mapNode)
            }
        }
        calculationLeftChildNode(leftChildNode)
        calculationRightChildNode(rightChildNode)
        invalidate()
    }

    private fun calculationThemeNode(nodeName: String, hasChild: Boolean) {
        val themeXMindNode = XMindNode(
            Rect(
                viewWidth / 2 - themeSize.width / 2,
                viewHeight / 2 - themeSize.height / 2,
                viewWidth / 2 + themeSize.width / 2,
                viewHeight / 2 + themeSize.height / 2
            ), nodeName, hasChild
        )
        themeNode = ThemeNode(context, themeXMindNode)
    }

    private fun calculationRightChildNode(rightChildNode: ArrayList<MapNode>) {
        val startX =
            (viewWidth / 2 + themeSize.width / 2 + nodeLeftRightMargin + childNodeSize.width)
        if (rightChildNode.size % 2 == 1) {
            calculationChildNodeOdd(startX, rightChildNode, false)
        } else {
            calculationModelNodeEven(startX, rightChildNode, false)
        }
    }

    private fun calculationModelNodeEven(
        startX: Int,
        childNode: java.util.ArrayList<MapNode>,
        isLeft: Boolean
    ) {
        childNode.forEachIndexed { index, mapNode ->
            if (index % 2 == 0) {
                val rect = Rect(
                    startX - childNodeSize.width,
                    viewHeight / 2 - nodeTopBottomMargin - childNodeSize.height
                            - (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2),
                    startX,
                    viewHeight / 2 - nodeTopBottomMargin - (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2)
                )
                val node = XMindNode(rect, mapNode.nodeName, mapNode.hasSubNode)
                childNodeList.add(ChildNode(context, node, isLeft))
            } else {
                val rect = Rect(
                    startX - childNodeSize.width,
                    viewHeight / 2 + nodeTopBottomMargin + (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2),
                    startX,
                    viewHeight / 2 + nodeTopBottomMargin + childNodeSize.height + (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2)
                )
                val node = XMindNode(rect, mapNode.nodeName, mapNode.hasSubNode)
                childNodeList.add(ChildNode(context, node, isLeft))
            }
        }
    }

    private fun calculationChildNodeOdd(
        startX: Int,
        childNode: java.util.ArrayList<MapNode>,
        isLeft: Boolean
    ) {
        childNode.forEachIndexed { index, mapNode ->
            if (index == 0) {
                val rect = Rect(
                    startX - childNodeSize.width,
                    viewHeight / 2 - childNodeSize.height / 2,
                    startX,
                    viewHeight / 2 + childNodeSize.height / 2
                )
                val node = XMindNode(rect, mapNode.nodeName, mapNode.hasSubNode)
                childNodeList.add(ChildNode(context, node, isLeft))
            } else {
                if (index % 2 == 0) {
                    val rect = Rect(
                        startX - childNodeSize.width,
                        viewHeight / 2 - childNodeSize.height / 2 + (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2),
                        startX,
                        viewHeight / 2 + childNodeSize.height / 2 + (2 * nodeTopBottomMargin + childNodeSize.height) * (index / 2)
                    )
                    val node = XMindNode(rect, mapNode.nodeName, mapNode.hasSubNode)
                    childNodeList.add(ChildNode(context, node, isLeft))
                } else {
                    val rect = Rect(
                        startX - childNodeSize.width,
                        viewHeight / 2 - childNodeSize.height / 2 - (2 * nodeTopBottomMargin + childNodeSize.height) * ((index + 1) / 2),
                        startX,
                        viewHeight / 2 + childNodeSize.height / 2 - (2 * nodeTopBottomMargin + childNodeSize.height) * ((index + 1) / 2)
                    )
                    val node = XMindNode(rect, mapNode.nodeName, mapNode.hasSubNode)
                    childNodeList.add(ChildNode(context, node, isLeft))
                }
            }
        }
    }

    private fun calculationLeftChildNode(leftChildNode: ArrayList<MapNode>) {
        val startX = (viewWidth / 2 - themeSize.width / 2 - nodeLeftRightMargin)
        if (leftChildNode.size % 2 == 1) {
            calculationChildNodeOdd(startX, leftChildNode, true)
        } else {
            calculationModelNodeEven(startX, leftChildNode, true)
        }
    }

    inner class XMindNodeSize(var width: Int, var height: Int)
}