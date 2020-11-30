package org.fireking.ap.custom.basic

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_histogram.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class HistogramActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<HistogramActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histogram)

        val oneBarList = ArrayList<Float>()
        oneBarList.add(1800F)
        oneBarList.add(760F)
        oneBarList.add(1400F)
        oneBarList.add(600F)
        oneBarList.add(3000F)

        val twoBarList = ArrayList<Float>()
        twoBarList.add(2000F)
        twoBarList.add(800F)
        twoBarList.add(1600F)
        twoBarList.add(400F)
        twoBarList.add(3200F)

        val labelList = ArrayList<String>()
        labelList.add("2017")
        labelList.add("2018")
        labelList.add("2019")
        labelList.add("2020")
        labelList.add("2021")
        barChart.setData(oneBarList, twoBarList, labelList)
    }
}
