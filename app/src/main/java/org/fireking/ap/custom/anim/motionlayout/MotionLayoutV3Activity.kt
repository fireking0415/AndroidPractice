package org.fireking.ap.custom.anim.motionlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class MotionLayoutV3Activity : AppCompatActivity() {

    companion object{

        @JvmStatic
        fun start(context: Context){
            context.startActivity(context.intentFor<MotionLayoutV3Activity>())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout_v3)
    }
}