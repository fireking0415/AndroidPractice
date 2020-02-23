package org.fireking.ap.custom.viewgroup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

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
