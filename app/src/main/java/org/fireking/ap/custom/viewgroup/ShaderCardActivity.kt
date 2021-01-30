package org.fireking.ap.custom.viewgroup

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class ShaderCardActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ShaderCardActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shader_card)
    }
}
