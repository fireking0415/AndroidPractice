package org.fireking.basic.image

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class GifLoadByCanvasActivity : AppCompatActivity() {
    
    companion object{
        @JvmStatic
        fun start(context: Context){
            context.startActivity(context.intentFor<GifLoadByCanvasActivity>())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_load_by_canvas)
    }
}