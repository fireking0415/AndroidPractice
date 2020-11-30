package org.fireking.ap

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.fireking.ap.custom.anim.AnimActivity
import org.fireking.ap.custom.basic.CanvasBasicActivity
import org.fireking.ap.custom.bezier.BasicBezierActivity
import org.fireking.ap.custom.constraintlayout.ConstraintLayoutSample
import org.fireking.ap.custom.coordinatorLayout.CoordinatorLayoutActivity
import org.fireking.ap.custom.flip.BasicFlipActivity
import org.fireking.ap.custom.font.TestFontActivity
import org.fireking.ap.custom.image.ImageActivity
import org.fireking.ap.custom.livedata.LiveDataViewModelActivity
import org.fireking.ap.custom.mpchart.MPChartActivity
import org.fireking.ap.custom.nested.NestedActivity
import org.fireking.ap.custom.newfunction.NewFunctionActivity
import org.fireking.ap.custom.notification.NotificationActivity
import org.fireking.ap.custom.path.BasicPathActivity
import org.fireking.ap.custom.recyclerview.RecyclerViewSampleActivity
import org.fireking.ap.custom.restudy.RestudyActivity
import org.fireking.ap.custom.thread.ThreadActivity
import org.fireking.ap.custom.viewevent.ViewEventSampleActivity
import org.fireking.ap.custom.viewgroup.CustomViewGroupActivity
import org.fireking.ap.custom.windowmanager.WindowManagerSampleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinatorLayout.setOnClickListener {
            CoordinatorLayoutActivity.start(this)
        }

        findViewById<Button>(R.id.btnCanvas).setOnClickListener {
            CanvasBasicActivity.startActivity(this@MainActivity)
        }

        findViewById<Button>(R.id.btnPath).setOnClickListener {
            BasicPathActivity.startActivity(this@MainActivity)
        }

        findViewById<Button>(R.id.btnFlip).setOnClickListener {
            BasicFlipActivity.startActivity(this@MainActivity)
        }

        findViewById<Button>(R.id.btnNotification).setOnClickListener {
            NotificationActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnBezier).setOnClickListener {
            BasicBezierActivity.startActivity(this@MainActivity)
        }

        findViewById<Button>(R.id.btnViewGroup).setOnClickListener {
            CustomViewGroupActivity.startActivity(this@MainActivity)
        }

        findViewById<Button>(R.id.btnNested).setOnClickListener {
            NestedActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnRecyclerView).setOnClickListener {
            RecyclerViewSampleActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnWindowManager).setOnClickListener {
            WindowManagerSampleActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnConstraintLayout).setOnClickListener {
            ConstraintLayoutSample.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnAnim).setOnClickListener {
            AnimActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnNewFunction).setOnClickListener {
            NewFunctionActivity.start(this@MainActivity)
        }

        btnThread.setOnClickListener {
            ThreadActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnViewEvent).setOnClickListener {
            ViewEventSampleActivity.start(this@MainActivity)
        }

        btnRestudy.setOnClickListener {
            RestudyActivity.start(this@MainActivity)
        }

        btnTestImage.setOnClickListener {
            ImageActivity.start(this@MainActivity)
        }

        btnLiveDataViewModel.setOnClickListener {
            LiveDataViewModelActivity.start(this@MainActivity)
        }

        btnTestFont.setOnClickListener {
            TestFontActivity.start(this@MainActivity)
        }

        btnMPLineChart.setOnClickListener {
            MPChartActivity.start(this)
        }
    }

}
