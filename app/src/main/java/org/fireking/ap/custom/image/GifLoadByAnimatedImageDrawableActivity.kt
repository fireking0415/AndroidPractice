package org.fireking.ap.custom.image

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gif_load_by_animated_image_drawable.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor


class GifLoadByAnimatedImageDrawableActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<GifLoadByAnimatedImageDrawableActivity>())
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_load_by_animated_image_drawable)

        val drawable =
            ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, R.mipmap.ic_banner))
        val scale = resources.displayMetrics.widthPixels * 1F / drawable.intrinsicWidth
        drawable.setBounds(
            0,
            0,
            resources.displayMetrics.widthPixels,
            (drawable.intrinsicHeight * scale).toInt()
        )
        iv_load_gif.setImageDrawable(drawable)
        if (drawable is AnimatedImageDrawable) {
            drawable.repeatCount = AnimatedImageDrawable.REPEAT_INFINITE
            drawable.start()
        }
    }
}