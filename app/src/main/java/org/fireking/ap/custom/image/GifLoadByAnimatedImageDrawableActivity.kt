package org.fireking.ap.custom.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityGifLoadByAnimatedImageDrawableBinding
import org.jetbrains.anko.intentFor


class GifLoadByAnimatedImageDrawableActivity : AppCompatActivity() {

    private var viewBinding: ActivityGifLoadByAnimatedImageDrawableBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<GifLoadByAnimatedImageDrawableActivity>())
        }
    }

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGifLoadByAnimatedImageDrawableBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        val drawable =
            ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, R.mipmap.ic_banner))
        val scale = resources.displayMetrics.widthPixels * 1F / drawable.intrinsicWidth
        drawable.setBounds(
            0,
            0,
            resources.displayMetrics.widthPixels,
            (drawable.intrinsicHeight * scale).toInt()
        )
        viewBinding?.ivLoadGif?.setImageDrawable(drawable)
        if (drawable is AnimatedImageDrawable) {
            drawable.repeatCount = AnimatedImageDrawable.REPEAT_INFINITE
            drawable.start()
        }
    }
}