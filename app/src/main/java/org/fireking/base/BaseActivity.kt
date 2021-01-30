package org.fireking.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var mViewBinding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
        fetchData()
    }

    open fun fetchData() {
    }

    fun bindView(block: VB.() -> Unit) {
        mViewBinding?.apply(block)
    }

    abstract fun initView()

    private fun initViewBinding() {
        if (null == mViewBinding) {
            val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
            for (item in parameterizedType.actualTypeArguments) {
                if (ViewBinding::class.java.isAssignableFrom(item as Class<*>)) {
                    val inflateMethod = item.getMethod("inflate", LayoutInflater::class.java)
                    mViewBinding = inflateMethod.invoke(null, layoutInflater) as VB
                    this.setContentView(mViewBinding!!.root)
                    break
                }
            }
            if (null == mViewBinding) {
                throw Exception("${this}ViewBinding is null")
            }
        }
    }
}