package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view_sticky.*
import org.fireking.ap.R
import org.fireking.ap.custom.recyclerview.v2.NewsAdapter
import org.jetbrains.anko.intentFor

class RecyclerViewStickyActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<RecyclerViewStickyActivity>())
        }
    }

    private lateinit var newAdapter: NewsAdapter
    private var isSticky: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_sticky)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            newAdapter.addItem()
        }

        newAdapter = NewsAdapter("测试Sticky")
        recyclerView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return super.canScrollVertically() && !isSticky
            }
        }
        recyclerView.adapter = newAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                isSticky = !recyclerView.canScrollVertically(1)
            }
        })

    }
}