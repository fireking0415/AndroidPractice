package org.fireking.basic.view.widget

data class MapNode(
    /**
     * 节点名称
     */
    var nodeName: String = "",

    /**
     * 节点层级
     */
    var level: Int = 0,

    /**
     * 是否存在子节点
     */
    var hasSubNode: Boolean = false,

    /**
     * 是否是主题界面
     */
    var isTheme: Boolean = false
)