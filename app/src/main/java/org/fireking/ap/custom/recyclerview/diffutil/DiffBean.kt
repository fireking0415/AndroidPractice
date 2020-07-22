package org.fireking.ap.custom.recyclerview.diffutil

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/7/22
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 * @author Wanggang
 */
data class DiffBean(
    val id: Int,
    val title: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiffBean

        if (id != other.id) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        return result
    }
}