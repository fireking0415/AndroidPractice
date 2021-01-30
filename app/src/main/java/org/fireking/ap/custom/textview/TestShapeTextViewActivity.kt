package org.fireking.ap.custom.textview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityTestShapeTextViewBinding
import org.fireking.library.kotlin.ext.intentFor

class TestShapeTextViewActivity : AppCompatActivity() {

    private var viewBinding: ActivityTestShapeTextViewBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TestShapeTextViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTestShapeTextViewBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
    }
}