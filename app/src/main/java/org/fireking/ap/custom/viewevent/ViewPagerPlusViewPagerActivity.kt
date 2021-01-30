package org.fireking.ap.custom.viewevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.viewevent.adapter.ViewPagerOutAdapter
import org.fireking.ap.databinding.ActivityViewPagerPlusViewPagerBinding
import org.fireking.library.kotlin.ext.intentFor

class ViewPagerPlusViewPagerActivity : AppCompatActivity() {

    private var viewBinding: ActivityViewPagerPlusViewPagerBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewPagerPlusViewPagerActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewPagerPlusViewPagerBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.outViewPager?.adapter = ViewPagerOutAdapter(supportFragmentManager)
    }
}