package org.fireking.ap.custom.restudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityRestudyBinding
import org.fireking.library.kotlin.ext.intentFor

class RestudyActivity : AppCompatActivity() {

    private var viewBinding: ActivityRestudyBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<RestudyActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRestudyBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnHandler?.setOnClickListener {
            HandlerV1Activity.start(this@RestudyActivity)
        }
    }
}