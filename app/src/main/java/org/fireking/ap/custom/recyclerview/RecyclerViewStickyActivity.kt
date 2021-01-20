package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R
import org.fireking.ap.custom.recyclerview.v2.News2Adapter
import org.fireking.ap.databinding.ActivityRecyclerViewStickyBinding
import org.jetbrains.anko.intentFor

class RecyclerViewStickyActivity : AppCompatActivity() {

    private var viewBinding: ActivityRecyclerViewStickyBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<RecyclerViewStickyActivity>())
        }
    }

    private lateinit var newAdapter: News2Adapter
    private var isSticky: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecyclerViewStickyBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            newAdapter.addItem()
        }

        newAdapter = News2Adapter()
        viewBinding?.recyclerView?.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return super.canScrollVertically() && !isSticky
            }
        }
        viewBinding?.recyclerView?.adapter = newAdapter

        viewBinding?.recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                isSticky = !recyclerView.canScrollVertically(1)
            }
        })

    }
}