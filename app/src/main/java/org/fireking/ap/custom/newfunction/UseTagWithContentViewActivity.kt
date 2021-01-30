package org.fireking.ap.custom.newfunction

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fireking.library.kotlin.ext.intentFor

class UseTagWithContentViewActivity : AppCompatActivity() {

    companion object {

        private const val activity_use_tag_with_content_view: String =
            "activity_use_tag_with_content_view"

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<UseTagWithContentViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_use_tag_with_content_view)
    }

    private fun setContentView(tag: String) {
        setContentView(resources.getIdentifier(tag, "layout", packageName))
    }
}