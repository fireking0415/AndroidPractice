package org.fireking.ap.custom.nested

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_nested_sample3.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class NestedSample3Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<NestedSample3Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_sample3)

        rv_content_list.layoutManager = LinearLayoutManager(this)
        rv_content_list.adapter = MainAdapter()
    }
}
