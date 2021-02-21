package org.fireking.basic.mpandroidchart.piechart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.fireking.ap.databinding.ActivityPieChartBinding
import org.fireking.library.kotlin.ext.intentFor

class PieChartActivity : AppCompatActivity() {

    private var viewBinding: ActivityPieChartBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<PieChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPieChartBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        drawPieChartView1()
    }

    private fun drawPieChartView1() {
        val pieEntities = ArrayList<PieEntry>()
        pieEntities.add(PieEntry(30F, "aaa"))
        pieEntities.add(PieEntry(70F, "bbb"))
        pieEntities.add(PieEntry(50F, "ccc"))

        val pieDataSet = PieDataSet(pieEntities, "label")
        pieDataSet.colors = arrayListOf(
            Color.parseColor("#334455"),
            Color.parseColor("#F14400"),
            Color.parseColor("#00FF33")
        )

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)

        viewBinding?.pieChatView?.data = pieData
        viewBinding?.pieChatView?.invalidate()
    }
}