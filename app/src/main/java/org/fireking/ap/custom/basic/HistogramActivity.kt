package org.fireking.ap.custom.basic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityHistogramBinding
import org.jetbrains.anko.intentFor

class HistogramActivity : AppCompatActivity() {

    private var viewBinding: ActivityHistogramBinding? = null

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<HistogramActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHistogramBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

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
        viewBinding?.barChart?.setData(oneBarList, twoBarList, labelList)
    }
}
