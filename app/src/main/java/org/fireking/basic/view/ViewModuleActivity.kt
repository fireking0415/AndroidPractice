package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityViewModuleBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class ViewModuleActivity : BaseActivity<ActivityViewModuleBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.intentFor<ViewModuleActivity>()
        }
    }

    override fun initView() {
        title = "View&Canvas"
        bindView {
            btnTestPath.setOnClickListener {
                PathTestV1Activity.start(this@ViewModuleActivity)
            }

            btnTaiChi.setOnClickListener {
                TaiChiDiagramActivity.start(this@ViewModuleActivity)
            }

            btnXmind.setOnClickListener {
                XMindActivity.start(this@ViewModuleActivity)
            }

            btnCubicLine.setOnClickListener {
                DrawCubicLineActivity.start(this@ViewModuleActivity)
            }

            btnHistogram.setOnClickListener {
                HistogramActivity.startActivity(this@ViewModuleActivity)
            }

            btnCircleColor.setOnClickListener {
                CircleColorActivity.startActivity(this@ViewModuleActivity)
            }

            btnLineChat.setOnClickListener {
                LineChatActivity.startActivity(this@ViewModuleActivity)
            }

            btnSpider.setOnClickListener {
                SpiderActivity.startActivity(this@ViewModuleActivity)
            }

            btnPieChat.setOnClickListener {
                PieChatActivity.startActivity(this@ViewModuleActivity)
            }

            btnLoadLayout.setOnClickListener {
                DrawLayoutActivity.start(this@ViewModuleActivity)
            }

            btnBezier.setOnClickListener {
                BasicBezierActivity.startActivity(this@ViewModuleActivity)
            }

            btnNumberFlip.setOnClickListener {
                NumberFlipActivity.startActivity(this@ViewModuleActivity)
            }
        }
    }
}