package org.fireking.ap.custom.basic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.basic.path.TestPathActivity
import org.fireking.ap.databinding.ActivityCanvasBasicBinding
import org.jetbrains.anko.intentFor

class CanvasBasicActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityCanvasBasicBinding

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<CanvasBasicActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityCanvasBasicBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBind.root)

        viewBind.btnTestPath.setOnClickListener {
            TestPathActivity.start(this)
        }

        viewBind.btnTaiChi.setOnClickListener {
            TaiChiDiagramActivity.start(this)
        }

        viewBind.btnXmind.setOnClickListener {
            XMindActivity.start(this)
        }

        viewBind.btnCubicLine.setOnClickListener {
            DrawCubicLineActivity.start(this)
        }

        viewBind.btnHistogram.setOnClickListener {
            HistogramActivity.startActivity(this@CanvasBasicActivity)
        }

        viewBind.btnCircleColor.setOnClickListener {
            CircleColorActivity.startActivity(this@CanvasBasicActivity)
        }

        viewBind.btnLineChat.setOnClickListener {
            LineChatActivity.startActivity(this@CanvasBasicActivity)
        }

        viewBind.btnSpider.setOnClickListener {
            SpiderActivity.startActivity(this@CanvasBasicActivity)
        }

        viewBind.btnPieChat.setOnClickListener {
            PieChatActivity.startActivity(this@CanvasBasicActivity)
        }

        viewBind.btnLoadLayout.setOnClickListener {
            DrawLayoutActivity.start(this)
        }
    }
}
