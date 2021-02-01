package org.fireking.basic.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityMagicIndicatorDemoBinding
import org.fireking.library.kotlin.ext.intentFor

class MagicIndicatorDemoActivity : AppCompatActivity() {

    private var viewBinding: ActivityMagicIndicatorDemoBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MagicIndicatorDemoActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMagicIndicatorDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        val tabs = arrayOf("交易机会1", "交易机会2", "交易机会3", "交易机会4", "交易机会5")
        tabs.forEachIndexed { _, text ->
            val tab = viewBinding?.tabLayout?.newTab()
            val layoutItemView = LayoutInflater.from(this@MagicIndicatorDemoActivity)
                .inflate(R.layout.custom_tablayout_item, null)
            layoutItemView.findViewById<TextView>(R.id.tv_label).text = text
            tab?.customView = layoutItemView
            tab?.let {
                viewBinding?.tabLayout?.addTab(tab)
            }
        }
        viewBinding?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }
}