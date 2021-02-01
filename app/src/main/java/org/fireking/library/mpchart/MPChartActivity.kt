package org.fireking.library.mpchart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.library.mpchart.barchart.BarChartSampleActivity
import org.fireking.library.mpchart.linechart.LineChartActivity
import org.fireking.library.mpchart.piechart.PieChartActivity
import org.fireking.library.mpchart.project.ProjectChartActivity
import org.fireking.library.mpchart.radarchart.RadarChartSampleActivity
import org.fireking.ap.databinding.ActivityMPChartBinding
import org.fireking.library.kotlin.ext.intentFor

class MPChartActivity : AppCompatActivity() {

    private var viewBinding: ActivityMPChartBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MPChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMPChartBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)


        viewBinding?.btnMPLineChartV1?.setOnClickListener {
            LineChartActivity.start(this)
        }

        viewBinding?.btnRadarChart?.setOnClickListener {
            RadarChartSampleActivity.start(this)
        }

        viewBinding?.btnBarChart?.setOnClickListener {
            BarChartSampleActivity.start(this)
        }

        viewBinding?.btnMpLineChart?.setOnClickListener {
            PieChartActivity.start(this)
        }

        viewBinding?.btnProjectSample?.setOnClickListener {
            ProjectChartActivity.start(this)
        }
    }
}