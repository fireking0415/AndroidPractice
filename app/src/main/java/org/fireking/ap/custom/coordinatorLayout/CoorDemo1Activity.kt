package org.fireking.ap.custom.coordinatorLayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityCoorDemo1Binding
import org.jetbrains.anko.intentFor

class CoorDemo1Activity : AppCompatActivity() {

    private var viewBinding: ActivityCoorDemo1Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<CoorDemo1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCoorDemo1Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.viewPager?.adapter = CoorDemo1Adapter(supportFragmentManager)
    }
}