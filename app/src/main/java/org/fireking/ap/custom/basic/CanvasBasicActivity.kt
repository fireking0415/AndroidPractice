package org.fireking.ap.custom.basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class CanvasBasicActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<CanvasBasicActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_basic)

        findViewById<Button>(R.id.btnHistogram).setOnClickListener {
            HistogramActivity.startActivity(this@CanvasBasicActivity)
        }

        findViewById<Button>(R.id.btnCircleColor).setOnClickListener {
            CircleColorActivity.startActivity(this@CanvasBasicActivity)
        }

        findViewById<Button>(R.id.btnLineChat).setOnClickListener {
            LineChatActivity.startActivity(this@CanvasBasicActivity)
        }

        findViewById<Button>(R.id.btnSpider).setOnClickListener {
            SpiderActivity.startActivity(this@CanvasBasicActivity)
        }

        findViewById<Button>(R.id.btnPieChat).setOnClickListener {
            PieChatActivity.startActivity(this@CanvasBasicActivity)
        }

        findViewById<Button>(R.id.btnLoadLayout).setOnClickListener {
            DrawLayoutActivity.start(this)
        }
    }
}
