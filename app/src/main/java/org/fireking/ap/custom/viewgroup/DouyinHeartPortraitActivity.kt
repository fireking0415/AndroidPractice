package org.fireking.ap.custom.viewgroup

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class DouyinHeartPortraitActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DouyinHeartPortraitActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_heart_portrait)
    }
}
