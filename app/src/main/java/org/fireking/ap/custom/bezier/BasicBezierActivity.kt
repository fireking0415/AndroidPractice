package org.fireking.ap.custom.bezier

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class BasicBezierActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<BasicBezierActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_bezier)

        findViewById<Button>(R.id.btnDoubleWave).setOnClickListener {
            DoubleWaveActivity.startActivity(this@BasicBezierActivity)
        }

        findViewById<Button>(R.id.btnSinusoidalWave).setOnClickListener {
            SinusoidalWaveActivity.startActivity(this@BasicBezierActivity)
        }
    }
}
