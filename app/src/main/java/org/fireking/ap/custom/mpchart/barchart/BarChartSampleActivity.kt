package org.fireking.ap.custom.mpchart.barchart

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bar_chart_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class BarChartSampleActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<BarChartSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart_sample)

        initBarChartV1()
    }

    private fun initBarChartV1() {
        barChartV1.setBackgroundColor(Color.parseColor("#80F14400"))
    }
}