package org.fireking.basic.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import okhttp3.*
import org.fireking.ap.databinding.ActivityGifLoadBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException

class GifLoadActivity : BaseActivity<ActivityGifLoadBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<GifLoadActivity>())
        }
    }

    private fun downloadGif(url: String) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@GifLoadActivity, "Gif下载失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val bytes = response.body?.bytes()
                runOnUiThread {
                    Toast.makeText(this@GifLoadActivity, "Gif下载成功", Toast.LENGTH_SHORT).show()
                    bindView {
                        bytes?.let {
                            val drawable = GifDrawable(it)
                            gifDrawable.setImageDrawable(drawable)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        bindView {
            if (gifDrawable.drawable is GifDrawable) {
                if ((gifDrawable.drawable as GifDrawable).isRunning) {
                    (gifDrawable.drawable as GifDrawable).stop()
                }
                (gifDrawable.drawable as GifDrawable).recycle()
            }
        }
        super.onDestroy()
    }

    override fun initView() {
        bindView {

            btnGifDrawable.setOnClickListener {
                downloadGif("http://geek-calendar-test.oss-cn-shanghai.aliyuncs.com/2020-08-10/1597050105581Vff159.gif")
            }

            btnGlideLoad.setOnClickListener {
                ivGlide.apply {
                    Glide.with(this@GifLoadActivity).asGif()
                        .load("http://geek-calendar-test.oss-cn-shanghai.aliyuncs.com/2020-08-10/1597050105581Vff159.gif")
                        .into(this)
                }
            }

            btnGlideTargetLoad.setOnClickListener {
                ivGlide.apply {
                    Glide.with(this@GifLoadActivity).asDrawable()
                        .load("http://geek-calendar-test.oss-cn-shanghai.aliyuncs.com/2020-08-10/1597050105581Vff159.gif")
                        .skipMemoryCache(true)
                        .override(200, 200)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(object : DrawableImageViewTarget(this) {
                            override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable?>?
                            ) {
                                super.onResourceReady(resource, transition)
                            }
                        })
                }
            }
        }
    }
}