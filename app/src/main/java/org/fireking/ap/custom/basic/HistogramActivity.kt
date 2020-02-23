package org.fireking.ap.custom.basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class HistogramActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context){
            context.startActivity(context.intentFor<HistogramActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histogram)
    }
}
