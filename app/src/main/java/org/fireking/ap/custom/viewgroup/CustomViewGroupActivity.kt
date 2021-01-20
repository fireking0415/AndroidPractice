package org.fireking.ap.custom.viewgroup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityCustomViewGroupBinding
import org.jetbrains.anko.intentFor

class CustomViewGroupActivity : AppCompatActivity() {

    private var viewBinding: ActivityCustomViewGroupBinding? = null

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<CustomViewGroupActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCustomViewGroupBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        findViewById<Button>(R.id.btnVerifyCodeInput).setOnClickListener {
            VerifyCodeInputLayoutActivity.startActivity(this@CustomViewGroupActivity)
        }

        findViewById<Button>(R.id.btnRadarLayout).setOnClickListener {
            RadarLayoutActivity.startActivity(this@CustomViewGroupActivity)
        }


        findViewById<Button>(R.id.btnCardView).setOnClickListener {
            ShaderCardActivity.start(this@CustomViewGroupActivity)
        }

        viewBinding?.btnTextAndView?.setOnClickListener {
            TextAndViewAlignActivity.start(this@CustomViewGroupActivity)
        }

        viewBinding?.btnDouyinHeader?.setOnClickListener {
            DouyinHeartPortraitActivity.start(this@CustomViewGroupActivity)
        }
    }
}
