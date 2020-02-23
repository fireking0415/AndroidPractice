package org.fireking.ap.custom.viewgroup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.fireking.ap.R
import org.fireking.ap.custom.viewgroup.view.VerifyCodeInputLayout
import org.jetbrains.anko.intentFor

class VerifyCodeInputLayoutActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<VerifyCodeInputLayoutActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code_input_layout)

        val verifyCodeInputLayout = findViewById<VerifyCodeInputLayout>(R.id.verifyCodeInputLayout)
        verifyCodeInputLayout.setOnCompleteListener(object : VerifyCodeInputLayout.OnCompleteListener {
            override fun onComplete(content: String) {
                Log.i("info", "输入完成/--->$content")
            }
        })
    }
}
