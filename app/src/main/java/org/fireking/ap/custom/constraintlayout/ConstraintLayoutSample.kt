package org.fireking.ap.custom.constraintlayout

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_constraint_layout_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ConstraintLayoutSample : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ConstraintLayoutSample>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_sample)

        btnV1.setOnClickListener {
            ConstaintLayoutV1.start(this@ConstraintLayoutSample)
        }
    }
}
