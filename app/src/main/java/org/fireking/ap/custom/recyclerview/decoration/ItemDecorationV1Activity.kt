package org.fireking.ap.custom.recyclerview.decoration

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        rv_content_list.addItemDecoration(
            ItemDecorationV1(
                this,
                5F,
                3,
                R.layout.decoration_space_layout
            )
        )
        rv_content_list.adapter = ItemDecorationV1Adapter()
    }
}