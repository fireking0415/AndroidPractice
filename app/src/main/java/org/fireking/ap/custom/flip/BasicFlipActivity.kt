package org.fireking.ap.custom.flip

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class BasicFlipActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<BasicFlipActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_flip)

        findViewById<Button>(R.id.btnFlipNumber).setOnClickListener {
            NumberFlipActivity.startActivity(this@BasicFlipActivity)
        }
    }
}
