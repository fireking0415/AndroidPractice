package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.custom.recyclerview.diffutil.DiffUtilActivity

class RecyclerViewSampleActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, RecyclerViewSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_sample)

        findViewById<Button>(R.id.btnCalendarV1).setOnClickListener {
            WanNianLiHomeActivity.start(this)
        }

        findViewById<Button>(R.id.btnCalendarV2).setOnClickListener {
            WanNianLiRecyclerView2Activity.start(this)
        }

        findViewById<Button>(R.id.btnCalendarV3).setOnClickListener {
            WanNianLiRecyclerView3Activity.start(this)
        }

        findViewById<Button>(R.id.btnDiffUtil).setOnClickListener {
            DiffUtilActivity.start(this@RecyclerViewSampleActivity)
        }
    }
}
