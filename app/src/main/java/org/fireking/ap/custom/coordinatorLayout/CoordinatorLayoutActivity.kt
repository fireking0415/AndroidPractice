package org.fireking.ap.custom.coordinatorLayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityCoordinatorLayoutBinding
import org.jetbrains.anko.intentFor

class CoordinatorLayoutActivity : AppCompatActivity() {

    private var viewBinding: ActivityCoordinatorLayoutBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<CoordinatorLayoutActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCoordinatorLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
        viewBinding?.btnDemo1?.setOnClickListener {
            CoorDemo1Activity.start(this)
        }
    }
}