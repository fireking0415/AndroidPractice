package org.fireking.ap.custom.image

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ImageActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ImageActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        btnTestGlideTarget.setOnClickListener {
            ImageTestActivity.start(this@ImageActivity)
        }

        btnTestGifMemory.setOnClickListener {
            GifLoadActivity.start(this@ImageActivity)
        }
    }
}