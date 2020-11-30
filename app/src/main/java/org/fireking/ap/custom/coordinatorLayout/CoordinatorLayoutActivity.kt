package org.fireking.ap.custom.coordinatorLayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coordinator_layout.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class CoordinatorLayoutActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<CoordinatorLayoutActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_layout)

        btnDemo1.setOnClickListener {
            CoorDemo1Activity.start(this)
        }
    }
}