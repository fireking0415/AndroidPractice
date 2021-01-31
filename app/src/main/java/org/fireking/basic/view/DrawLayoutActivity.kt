package org.fireking.basic.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class DrawLayoutActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DrawLayoutActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_layout)
    }
}