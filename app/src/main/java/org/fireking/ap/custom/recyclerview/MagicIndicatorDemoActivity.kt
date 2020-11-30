package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_magic_indicator_demo.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class MagicIndicatorDemoActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MagicIndicatorDemoActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magic_indicator_demo)

        val tabs = arrayOf("交易机会1", "交易机会2", "交易机会3", "交易机会4", "交易机会5")
        tabs.forEachIndexed { _, text ->
            val tab = tabLayout.newTab()
            val layoutItemView = LayoutInflater.from(this@MagicIndicatorDemoActivity)
                .inflate(R.layout.custom_tablayout_item, null)
            layoutItemView.findViewById<TextView>(R.id.tv_label).text = text
            tab.customView = layoutItemView
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }
}