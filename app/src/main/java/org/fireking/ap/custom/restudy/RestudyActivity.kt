package org.fireking.ap.custom.restudy

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_restudy.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class RestudyActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<RestudyActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restudy)

        btnHandler.setOnClickListener {
            HandlerV1Activity.start(this@RestudyActivity)
        }
    }
}