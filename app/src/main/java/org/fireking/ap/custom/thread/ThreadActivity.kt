package org.fireking.ap.custom.thread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityThread2Binding
import org.fireking.library.kotlin.ext.intentFor

class ThreadActivity : AppCompatActivity() {

    private var viewBinding: ActivityThread2Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ThreadActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityThread2Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnCreateThread?.setOnClickListener {
            Thread(Runnable {
                println("创建线程-->" + Thread.currentThread())
                Thread.sleep(12000)
            }).start()
        }
    }
}