package org.fireking.ap

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.fireking.ap.custom.anim.AnimActivity
import org.fireking.ap.custom.arouter.ARouterSampleActivity
import org.fireking.ap.custom.basic.CanvasBasicActivity
import org.fireking.ap.custom.bezier.BasicBezierActivity
import org.fireking.ap.custom.constraintlayout.ConstraintLayoutSample
import org.fireking.ap.custom.coordinatorLayout.CoordinatorLayoutActivity
import org.fireking.ap.custom.flip.BasicFlipActivity
import org.fireking.ap.custom.image.ImageActivity
import org.fireking.ap.custom.livedata.LiveDataViewModelActivity
import org.fireking.ap.custom.mpchart.MPChartActivity
import org.fireking.ap.custom.nested.NestedActivity
import org.fireking.ap.custom.newfunction.NewFunctionActivity
import org.fireking.ap.custom.notification.NotificationActivity
import org.fireking.ap.custom.path.BasicPathActivity
import org.fireking.ap.custom.recyclerview.RecyclerViewSampleActivity
import org.fireking.ap.custom.restudy.RestudyActivity
import org.fireking.ap.custom.textview.TestTextViewActivity
import org.fireking.ap.custom.thread.ThreadActivity
import org.fireking.ap.custom.viewevent.ViewEventSampleActivity
import org.fireking.ap.custom.viewgroup.CustomViewGroupActivity
import org.fireking.ap.custom.windowmanager.WindowManagerSampleActivity
import org.fireking.ap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.coordinatorLayout.setOnClickListener {
            CoordinatorLayoutActivity.start(this)
        }

        viewBinding.btnCanvas.setOnClickListener {
            CanvasBasicActivity.startActivity(this@MainActivity)
        }

        viewBinding.btnPath.setOnClickListener {
            BasicPathActivity.startActivity(this@MainActivity)
        }

        viewBinding.btnFlip.setOnClickListener {
            BasicFlipActivity.startActivity(this@MainActivity)
        }

        viewBinding.btnNotification.setOnClickListener {
            NotificationActivity.start(this@MainActivity)
        }

        viewBinding.btnBezier.setOnClickListener {
            BasicBezierActivity.startActivity(this@MainActivity)
        }

        viewBinding.btnViewGroup.setOnClickListener {
            CustomViewGroupActivity.startActivity(this@MainActivity)
        }

        viewBinding.btnNested.setOnClickListener {
            NestedActivity.start(this@MainActivity)
        }

        viewBinding.btnRecyclerView.setOnClickListener {
            RecyclerViewSampleActivity.start(this@MainActivity)
        }

        viewBinding.btnWindowManager.setOnClickListener {
            WindowManagerSampleActivity.start(this@MainActivity)
        }

        viewBinding.btnConstraintLayout.setOnClickListener {
            ConstraintLayoutSample.start(this@MainActivity)
        }

        viewBinding.btnAnim.setOnClickListener {
            AnimActivity.start(this@MainActivity)
        }

        viewBinding.btnNewFunction.setOnClickListener {
            NewFunctionActivity.start(this@MainActivity)
        }

        btnThread.setOnClickListener {
            ThreadActivity.start(this@MainActivity)
        }

        viewBinding.btnViewEvent.setOnClickListener {
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

        btnTestTextView.setOnClickListener {
            TestTextViewActivity.start(this@MainActivity)
        }

        btnMPLineChart.setOnClickListener {
            MPChartActivity.start(this)
        }

        viewBinding.btnTestARouter.setOnClickListener {
            ARouterSampleActivity.start(this@MainActivity)
        }
    }

}
