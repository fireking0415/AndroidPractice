package org.fireking.basic.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityRecyclerViewSampleBinding
import org.fireking.basic.recyclerview.decoration.ItemDecorationV1Activity
import org.fireking.basic.recyclerview.diffutil.DiffUtilActivity

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

        findViewById<Button>(R.id.btnTestDecoration).setOnClickListener {
            ItemDecorationV1Activity.start(this)
        }

        findViewById<Button>(R.id.btnDiffUtil).setOnClickListener {
            DiffUtilActivity.start(this@RecyclerViewSampleActivity)
        }
    }
}
