package org.fireking.ap.custom.basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class DrawCubicLineActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context){
            context.startActivity(context.intentFor<DrawCubicLineActivity>())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_cubic_line)
    }
}