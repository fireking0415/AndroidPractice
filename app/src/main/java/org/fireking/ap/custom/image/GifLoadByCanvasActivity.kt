package org.fireking.ap.custom.image

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

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