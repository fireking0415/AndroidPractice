package org.fireking.library.kotlin.ext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

class IntentHelper {

    companion object {

        @JvmStatic
        fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
            val intent = Intent(ctx, clazz)
            if (params.isNotEmpty()) {
                fullIntentArguments(intent, params)
            }
            return intent
        }

        private fun fullIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
            params.forEach {
                when (val value = it.second) {
                    null -> intent.putExtra(it.first, null as Serializable?)
                    is Int -> intent.putExtra(it.first, value)
                    is Long -> intent.putExtra(it.first, value)
                    is CharSequence -> intent.putExtra(it.first, value)
                    is String -> intent.putExtra(it.first, value)
                    is Float -> intent.putExtra(it.first, value)
                    is Double -> intent.putExtra(it.first, value)
                    is Char -> intent.putExtra(it.first, value)
                    is Short -> intent.putExtra(it.first, value)
                    is Boolean -> intent.putExtra(it.first, value)
                    is Serializable -> intent.putExtra(it.first, value)
                    is Bundle -> intent.putExtra(it.first, value)
                    is Parcelable -> intent.putExtra(it.first, value)
                    is Array<*> -> when {
                        value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                        value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                        value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                        else -> throw IntentParamException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                    }
                    is IntArray -> intent.putExtra(it.first, value)
                    is LongArray -> intent.putExtra(it.first, value)
                    is FloatArray -> intent.putExtra(it.first, value)
                    is DoubleArray -> intent.putExtra(it.first, value)
                    is CharArray -> intent.putExtra(it.first, value)
                    is ShortArray -> intent.putExtra(it.first, value)
                    is BooleanArray -> intent.putExtra(it.first, value)
                    else -> throw IntentParamException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
                return@forEach
            }
        }

        @JvmStatic
        fun bundleOf(vararg params: Pair<String, Any?>): Bundle {
            val b = Bundle()
            for (p in params) {
                val (k, v) = p
                when (v) {
                    null -> b.putSerializable(k, null)
                    is Boolean -> b.putBoolean(k, v)
                    is Byte -> b.putByte(k, v)
                    is Char -> b.putChar(k, v)
                    is Short -> b.putShort(k, v)
                    is Int -> b.putInt(k, v)
                    is Long -> b.putLong(k, v)
                    is Float -> b.putFloat(k, v)
                    is Double -> b.putDouble(k, v)
                    is String -> b.putString(k, v)
                    is CharSequence -> b.putCharSequence(k, v)
                    is Parcelable -> b.putParcelable(k, v)
                    is Serializable -> b.putSerializable(k, v)
                    is BooleanArray -> b.putBooleanArray(k, v)
                    is ByteArray -> b.putByteArray(k, v)
                    is CharArray -> b.putCharArray(k, v)
                    is DoubleArray -> b.putDoubleArray(k, v)
                    is FloatArray -> b.putFloatArray(k, v)
                    is IntArray -> b.putIntArray(k, v)
                    is LongArray -> b.putLongArray(k, v)
                    is Array<*> -> {
                        @Suppress("UNCHECKED_CAST")
                        when {
                            v.isArrayOf<Parcelable>() -> b.putParcelableArray(k, v as Array<out Parcelable>)
                            v.isArrayOf<CharSequence>() -> b.putCharSequenceArray(k, v as Array<out CharSequence>)
                            v.isArrayOf<String>() -> b.putStringArray(k, v as Array<out String>)
                            else -> throw IntentParamException("Unsupported bundle component (${v.javaClass})")
                        }
                    }
                    is ShortArray -> b.putShortArray(k, v)
                    is Bundle -> b.putBundle(k, v)
                    else -> throw IntentParamException("Unsupported bundle component (${v.javaClass})")
                }
            }
            return b
        }
    }

    open class IntentParamException(message: String = "") : RuntimeException(message)
}