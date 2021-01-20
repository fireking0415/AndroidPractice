package org.fireking.ap.custom.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityViewPagerRecyclerOOMViewBinding
import org.jetbrains.anko.intentFor

class ViewPagerRecyclerOOMViewActivity : AppCompatActivity() {

    private var viewBinding: ActivityViewPagerRecyclerOOMViewBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ViewPagerRecyclerOOMViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewPagerRecyclerOOMViewBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
        viewBinding?.viewPager?.adapter = ViewPagerRecyclerViewOOMAdapter(
            arrayListOf("财富", "故事", "Python", "Java", "OOM", "怎么了"),
            this
        )
    }
}