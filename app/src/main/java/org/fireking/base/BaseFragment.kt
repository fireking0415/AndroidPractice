package org.fireking.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var mViewBinding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getRootView(inflater, container)
    }

    private fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View? {
        if (null == mViewBinding) {
            val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
            for (item in parameterizedType.actualTypeArguments) {
                if (ViewBinding::class.java.isAssignableFrom(item as Class<*>)) {
                    val inflateMethod = item.getMethod(
                        "inflate",
                        LayoutInflater::class.java,
                        ViewGroup::class.java,
                        Boolean::class.java
                    )
                    mViewBinding =
                        inflateMethod.invoke(null, inflater, container, false) as VB
                    return mViewBinding!!.root
                }
            }
        } else {
            return mViewBinding?.root
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
    }

    open fun loadData() {
    }

    abstract fun initView()

    fun bindView(block: VB.() -> Unit) {
        mViewBinding?.apply(block)
    }
}