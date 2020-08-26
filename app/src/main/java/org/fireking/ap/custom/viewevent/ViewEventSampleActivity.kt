package org.fireking.ap.custom.viewevent

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_event_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ViewEventSampleActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewEventSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_event_sample)

        btnViewPagerPlusViewPager.setOnClickListener {
            ViewPagerPlusViewPagerActivity.start(this@ViewEventSampleActivity)
        }
    }
}