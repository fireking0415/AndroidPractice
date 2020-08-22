package org.fireking.ap.custom.viewevent

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_pager_plus_view_pager.*
import org.fireking.ap.R
import org.fireking.ap.custom.viewevent.adapter.ViewPagerOutAdapter
import org.jetbrains.anko.intentFor

class ViewPagerPlusViewPagerActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewPagerPlusViewPagerActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_plus_view_pager)

        outViewPager.adapter = ViewPagerOutAdapter(supportFragmentManager)
    }
}