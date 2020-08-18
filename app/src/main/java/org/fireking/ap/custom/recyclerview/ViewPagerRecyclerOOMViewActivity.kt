package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_pager_recycler_o_o_m_view.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ViewPagerRecyclerOOMViewActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewPagerRecyclerOOMViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_recycler_o_o_m_view)
        viewPager.adapter = ViewPagerRecyclerViewOOMAdapter(
            arrayListOf("财富", "故事", "Python", "Java", "OOM", "怎么了"),
            this
        )
    }
}