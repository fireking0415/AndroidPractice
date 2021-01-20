package org.fireking.ap.custom.restudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityHandlerV1Binding
import org.jetbrains.anko.intentFor

class HandlerV1Activity : AppCompatActivity() {

    private var viewBinding: ActivityHandlerV1Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<HandlerV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHandlerV1Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnUseUIThread?.setOnClickListener {
            val handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                }
            }
            handler.sendMessage(handler.obtainMessage(1))
        }
        viewBinding?.btnUseSubThread?.setOnClickListener {
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