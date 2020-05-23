package org.fireking.ap

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.anim.AnimActivity
import org.fireking.ap.custom.basic.CanvasBasicActivity
import org.fireking.ap.custom.bezier.BasicBezierActivity
import org.fireking.ap.custom.flip.BasicFlipActivity
import org.fireking.ap.custom.nested.NestedActivity
import org.fireking.ap.custom.newfunction.NewFunctionActivity
import org.fireking.ap.custom.notification.NotificationActivity
import org.fireking.ap.custom.path.BasicPathActivity
import org.fireking.ap.custom.recyclerview.RecyclerViewSampleActivity
import org.fireking.ap.custom.viewgroup.CustomViewGroupActivity
import org.fireking.ap.custom.windowmanager.WindowManagerSampleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        findViewById<Button>(R.id.btnAnim).setOnClickListener {
            AnimActivity.start(this@MainActivity)
        }

        findViewById<Button>(R.id.btnNewFunction).setOnClickListener {
            NewFunctionActivity.start(this@MainActivity)
        }

    }

}
