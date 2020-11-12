package org.fireking.ap.custom.recyclerview.decoration

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_item_decoration_v1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ItemDecorationV1Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ItemDecorationV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_decoration_v1)

        rv_content_list.layoutManager = LinearLayoutManager(this)
        val stickyView = LayoutInflater.from(this)
            .inflate(R.layout.decoration_space_layout, rv_content_list, false)
        stickyView.findViewById<ImageView>(R.id.iv_launcher).setOnClickListener {
            Toast.makeText(this, "点击了图标", Toast.LENGTH_SHORT).show()
        }
        val itemDecoration = PowerStickyItemDecoration(
            context = this,
            stickyView = stickyView,
            stickyPosition = 2
        )
        rv_content_list.addItemDecoration(itemDecoration)
        rv_content_list.addOnItemTouchListener(
            PowerStickyItemTouchListener(
                rv_content_list,
                itemDecoration
            )
        )
        rv_content_list.adapter = ItemDecorationV1Adapter()
    }
}