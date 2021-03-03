package org.fireking.basic.recyclerview.diffutil

import java.io.Serializable

data class DiffBean(
    var id: Int = 0,
    var title: String = "",
    var desc: String = ""
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiffBean

        if (id != other.id) return false
        if (title != other.title) return false
        if (desc != other.desc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + desc.hashCode()
        return result
    }

    override fun toString(): String {
        return "DiffBean(id=$id, title='$title', desc='$desc')"
    }
}