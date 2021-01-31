package org.fireking.ap.custom.recyclerview.diffutil

data class DiffBean(
    var id: Int,
    var title: String
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