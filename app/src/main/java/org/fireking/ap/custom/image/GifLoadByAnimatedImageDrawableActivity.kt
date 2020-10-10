package org.fireking.ap.custom.image

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class GifLoadByAnimatedImageDrawableActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<GifLoadByAnimatedImageDrawableActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_load_by_animated_image_drawable)
    }
}