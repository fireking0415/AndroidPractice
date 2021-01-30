package org.fireking.ap.custom.path

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class BasicPathActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<PathMeasureActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_path)

        findViewById<Button>(R.id.btnPathMeasure).setOnClickListener {

            PathMeasureActivity.startActivity(this@BasicPathActivity)
        }
    }
}
