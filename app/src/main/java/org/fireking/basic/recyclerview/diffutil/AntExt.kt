package org.fireking.basic.recyclerview.diffutil

import com.esotericsoftware.kryo.Kryo

fun <T : Any> T.deepCopy(): T {
    val kryo = Kryo()
    kryo.register(this.javaClass)
    return kryo.copy(this)
}