package org.fireking.ap.custom.constraintlayout

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ConstaintLayoutV1 : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ConstaintLayoutV1>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constaint_layout_v1)
    }
}
