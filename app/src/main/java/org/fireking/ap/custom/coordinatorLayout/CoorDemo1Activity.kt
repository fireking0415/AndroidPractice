package org.fireking.ap.custom.coordinatorLayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coor_demo1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class CoorDemo1Activity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<CoorDemo1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coor_demo1)

        view_pager.adapter = CoorDemo1Adapter(supportFragmentManager)
    }
}