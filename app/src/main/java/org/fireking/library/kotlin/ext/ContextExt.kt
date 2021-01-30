package org.fireking.library.kotlin.ext

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/**
 * 简化Activity跳转
 *
 * <p>
 *   private const val PARAM_STOCK = "stock"
 *
 *   @JvmStatic
 *   fun startActivity(context: Context, stock: Stock) {
 *       context.startActivity(context.intentFor<DiagnosisDetailActivity>(
 *           PARAM_STOCK to stock
 *       ))
 *   }
 * </p>
 */
inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent =
    IntentHelper.createIntent(this, T::class.java, params)

/**
 * 简化Fragment创建传惨
 *
 *   @JvmStatic
 *   fun createFragment(stockCode: Stock): HeaderFragment {
 *      return HeaderFragment().withArguments(PARAM_STOCK to stockCode)
 *   }
 * </p>
 */
inline fun <reified T : Fragment> T.withArguments(vararg param: Pair<String, Any?>): T {
    arguments = bundleOf(*param)
    return this
}