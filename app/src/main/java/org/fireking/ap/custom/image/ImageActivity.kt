package org.fireking.ap.custom.image

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityImageBinding
import org.jetbrains.anko.intentFor

class ImageActivity : AppCompatActivity() {

    private var viewBinding: ActivityImageBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ImageActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityImageBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnTestGlideTarget?.setOnClickListener {
            ImageTestActivity.start(this@ImageActivity)
        }

        viewBinding?.btnTestGifMemory?.setOnClickListener {
            GifLoadActivity.start(this@ImageActivity)
        }

        viewBinding?.btnLoadGifByCanvas?.setOnClickListener {
            GifLoadByCanvasActivity.start(this@ImageActivity)
        }

        viewBinding?.btnLoadGifByAnimatedImageDrawable?.setOnClickListener {
            GifLoadByAnimatedImageDrawableActivity.start(this@ImageActivity)
        }

        viewBinding?.btnMatrix?.setOnClickListener {
            MatrixTransformActivity.start(this@ImageActivity)
        }
    }
}