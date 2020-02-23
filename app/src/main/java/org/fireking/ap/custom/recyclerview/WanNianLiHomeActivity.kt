package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import kotlinx.android.synthetic.main.activity_wan_nian_li_home.*
import org.fireking.ap.R
import org.fireking.ap.custom.recyclerview.adapter.WanNianLiBodyAdapter
import org.fireking.ap.custom.recyclerview.adapter.WanNianLiHeaderAdapter
import org.jetbrains.anko.intentFor

class WanNianLiHomeActivity : AppCompatActivity() {

    private lateinit var delegateAdapter: DelegateAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<WanNianLiHomeActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wan_nian_li_home)

        val virtualLayoutManager = VirtualLayoutManager(this)
        outRecyclerView.layoutManager = virtualLayoutManager
        val viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(0, 10)

        delegateAdapter = DelegateAdapter(virtualLayoutManager)
        outRecyclerView.adapter = delegateAdapter

        val headerAdapter = WanNianLiHeaderAdapter()
        delegateAdapter.addAdapter(headerAdapter)

        val bodyAdapter = WanNianLiBodyAdapter()
        delegateAdapter.addAdapter(bodyAdapter)
    }
}
