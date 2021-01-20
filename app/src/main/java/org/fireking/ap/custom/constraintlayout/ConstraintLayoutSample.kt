package org.fireking.ap.custom.constraintlayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityConstraintLayoutSampleBinding
import org.jetbrains.anko.intentFor

class ConstraintLayoutSample : AppCompatActivity() {

    private var viewBinding: ActivityConstraintLayoutSampleBinding? = null

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ConstraintLayoutSample>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityConstraintLayoutSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnV1?.setOnClickListener {
            ConstaintLayoutV1.start(this@ConstraintLayoutSample)
        }
    }
}
