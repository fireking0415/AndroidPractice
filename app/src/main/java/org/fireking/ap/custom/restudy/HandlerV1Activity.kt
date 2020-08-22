package org.fireking.ap.custom.restudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_handler_v1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class HandlerV1Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<HandlerV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_v1)

        btnUseUIThread.setOnClickListener {
            val handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                }
            }
            handler.sendMessage(handler.obtainMessage(1))
        }
        btnUseSubThread.setOnClickListener {
            Thread(){
                Looper.prepare()
                val handler = object: Handler(){
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)
                    }
                }
                Looper.loop()
            }.start()
        }
    }
}