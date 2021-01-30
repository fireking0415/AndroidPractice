package org.fireking.ap.custom.basic.path

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityTestPathBinding
import org.fireking.library.kotlin.ext.intentFor

class TestPathActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTestPathBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TestPathActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTestPathBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnTestPathV1.setOnClickListener {
            PathTestV1Activity.start(this)
        }
    }
}