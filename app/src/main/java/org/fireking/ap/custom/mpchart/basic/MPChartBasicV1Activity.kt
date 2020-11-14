package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class MPChartBasicV1Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MPChartBasicV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p_chart_basic_v1)
    }
}