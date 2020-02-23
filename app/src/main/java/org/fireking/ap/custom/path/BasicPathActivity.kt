package org.fireking.ap.custom.path

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

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
