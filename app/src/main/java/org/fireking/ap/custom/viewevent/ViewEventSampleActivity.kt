package org.fireking.ap.custom.viewevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityViewEventSampleBinding
import org.jetbrains.anko.intentFor

class ViewEventSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityViewEventSampleBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewEventSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewEventSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnViewPagerPlusViewPager?.setOnClickListener {
            ViewPagerPlusViewPagerActivity.start(this@ViewEventSampleActivity)
        }
    }
}