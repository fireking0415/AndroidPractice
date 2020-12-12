package org.fireking.ap.custom.textview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityTestTextViewBinding
import org.jetbrains.anko.intentFor

class TestTextViewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTestTextViewBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TestTextViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTestTextViewBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnClearBorder.setOnClickListener {
            TextViewClearBorderActivity.start(this)
        }

        viewBinding.btnTestFont.setOnClickListener {
            TestFontActivity.start(this)
        }
    }
}