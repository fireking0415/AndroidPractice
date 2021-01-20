package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.custom.recyclerview.decoration.ItemDecorationV1Activity
import org.fireking.ap.custom.recyclerview.diffutil.DiffUtil2Activity
import org.fireking.ap.custom.recyclerview.diffutil.DiffUtilActivity
import org.fireking.ap.databinding.ActivityRecyclerViewSampleBinding

class RecyclerViewSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityRecyclerViewSampleBinding? = null

    companion object {

        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, RecyclerViewSampleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecyclerViewSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        findViewById<Button>(R.id.btnTabLayout).setOnClickListener {
            MagicIndicatorDemoActivity.start(this)
        }

        findViewById<Button>(R.id.btnTestDecoration).setOnClickListener {
            ItemDecorationV1Activity.start(this)
        }

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

        viewBinding?.btnDiffUtil2?.setOnClickListener {
            DiffUtil2Activity.start(this@RecyclerViewSampleActivity)
        }

        viewBinding?.btnSticky?.setOnClickListener {
            RecyclerViewStickyActivity.start(this@RecyclerViewSampleActivity)
        }

        viewBinding?.btnOOM?.setOnClickListener {
            ViewPagerRecyclerOOMViewActivity.start(this@RecyclerViewSampleActivity)
        }
    }
}
