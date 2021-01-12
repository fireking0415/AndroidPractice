package org.fireking.ap.custom.arouter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityArouterSampleBinding
import org.jetbrains.anko.intentFor

class ARouterSampleActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityArouterSampleBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ARouterSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityArouterSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnCallService.setOnClickListener {
//            ARouter.getInstance().navigation()
        }
        viewBinding.btnNavigation.setOnClickListener {

        }
    }
}