package org.fireking.ap.custom.basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

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
