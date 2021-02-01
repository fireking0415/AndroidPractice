package org.fireking.basic.nested

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.ActivityNestedSample3Binding
import org.fireking.library.kotlin.ext.intentFor

class NestedSample3Activity : AppCompatActivity() {

    private var viewBinding: ActivityNestedSample3Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<NestedSample3Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNestedSample3Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.contentList?.layoutManager = LinearLayoutManager(this)
        viewBinding?.contentList?.adapter = MainAdapter()
    }
}
