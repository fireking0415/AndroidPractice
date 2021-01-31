package org.fireking.basic.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class PieChatActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<PieChatActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chat)
    }
}
