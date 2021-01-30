package org.fireking.ap.custom.recyclerview.decoration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.fireking.ap.databinding.ActivityItemDecorationV1Binding
import org.fireking.ap.databinding.DecorationSpaceLayoutBinding
import org.fireking.library.kotlin.ext.intentFor

class ItemDecorationV1Activity : AppCompatActivity() {

    private var viewBinding: ActivityItemDecorationV1Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ItemDecorationV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityItemDecorationV1Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.rvContentList?.layoutManager = LinearLayoutManager(this)
        val stickyView = DecorationSpaceLayoutBinding.inflate(
            LayoutInflater.from(this),
            viewBinding?.rvContentList,
            false
        )
        stickyView.ivLauncher.setOnClickListener {
            Toast.makeText(this, "点击了图标", Toast.LENGTH_SHORT).show()
        }
        val itemDecoration = PowerStickyItemDecoration(
            context = this,
            stickyView = stickyView.root,
            stickyPosition = 2
        )
        viewBinding?.rvContentList?.addItemDecoration(itemDecoration)
        viewBinding?.rvContentList?.addOnItemTouchListener(
            PowerStickyItemTouchListener(
                viewBinding?.rvContentList!!,
                itemDecoration
            )
        )
        viewBinding?.rvContentList?.adapter = ItemDecorationV1Adapter()
    }
}