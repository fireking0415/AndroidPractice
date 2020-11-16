package org.fireking.ap.custom.mpchart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_m_p_chart.*
import org.fireking.ap.R
import org.fireking.ap.custom.mpchart.basic.LineChartActivity
import org.fireking.ap.custom.mpchart.basic.PieChartActivity
import org.jetbrains.anko.intentFor

class MPChartActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MPChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p_chart)


        btnMPLineChartV1.setOnClickListener {
            LineChartActivity.start(this)
        }

        btnMpLineChart.setOnClickListener {
            PieChartActivity.start(this)
        }
    }
}