package org.fireking.ap.custom.thread

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_thread2.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ThreadActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ThreadActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread2)

        btnCreateThread.setOnClickListener {
            Thread(Runnable {
                println("创建线程-->" + Thread.currentThread())
                Thread.sleep(12000)
            }).start()
        }
    }
}