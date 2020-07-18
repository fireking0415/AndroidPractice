package org.fireking.ap.custom.viewgroup

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_view_group.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class CustomViewGroupActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<CustomViewGroupActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_group)

        findViewById<Button>(R.id.btnVerifyCodeInput).setOnClickListener {
            VerifyCodeInputLayoutActivity.startActivity(this@CustomViewGroupActivity)
        }

        findViewById<Button>(R.id.btnRadarLayout).setOnClickListener {
            RadarLayoutActivity.startActivity(this@CustomViewGroupActivity)
        }


        findViewById<Button>(R.id.btnCardView).setOnClickListener {
            ShaderCardActivity.start(this@CustomViewGroupActivity)
        }

        btnTextAndView.setOnClickListener {
            TextAndViewAlignActivity.start(this@CustomViewGroupActivity)
        }

        btnDouyinHeader.setOnClickListener {
            DouyinHeartPortraitActivity.start(this@CustomViewGroupActivity)
        }
    }
}
