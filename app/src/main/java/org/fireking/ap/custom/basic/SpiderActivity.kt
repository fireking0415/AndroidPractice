package org.fireking.ap.custom.basic

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.basic.view.SpiderView
import org.fireking.ap.databinding.ActivitySpiderBinding
import org.jetbrains.anko.intentFor
import java.util.*

class SpiderActivity : AppCompatActivity() {

    private var viewBinding: ActivitySpiderBinding? = null

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<SpiderActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySpiderBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.spiderView?.setSpiderColor(
            Color.argb(
                120,
                (255 * Math.random()).toInt(),
                (255 * Math.random()).toInt(),
                (255 * Math.random()).toInt()
            )
        )
        //设置展示文字颜色
        viewBinding?.spiderView?.setLetterColor(Color.parseColor("#364e68"))
        //设置网格颜色
        viewBinding?.spiderView?.setGridColor(Color.parseColor("#364e68"))
        //设置数据
        val dataList = ArrayList<SpiderView.SpiderData>()
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "KDA"))
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "发育"))
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "团战"))
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "生存"))
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "输出"))
        dataList.add(SpiderView.SpiderData((Math.random() * 300).toInt(), "其他"))
        viewBinding?.spiderView?.setData(dataList)
    }
}
