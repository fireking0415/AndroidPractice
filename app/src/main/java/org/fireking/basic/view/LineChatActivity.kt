package org.fireking.basic.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class LineChatActivity : AppCompatActivity() {

    companion object{

        @JvmStatic
        fun startActivity(context: Context){
            context.startActivity(context.intentFor<LineChatActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chat)
    }
}
