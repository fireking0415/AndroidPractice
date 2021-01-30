package org.fireking.ap.custom.notification

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityNotificationBinding
import org.fireking.library.kotlin.ext.intentFor

class NotificationActivity : AppCompatActivity() {

    private var viewBinding: ActivityNotificationBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<NotificationActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotificationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
    }
}
