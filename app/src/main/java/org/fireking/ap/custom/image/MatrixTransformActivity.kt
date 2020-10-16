package org.fireking.ap.custom.image

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import kotlinx.android.synthetic.main.activity_matrix_transform.*
import org.fireking.ap.R
import org.fireking.ap.custom.image.widget.MatrixDescDialog
import org.jetbrains.anko.intentFor

class MatrixTransformActivity : AppCompatActivity() {

    private var matrixDescDialog: BasePopupView? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MatrixTransformActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix_transform)
        initView()
        initListener()
    }

    private fun initView() {
        matrixDescDialog = XPopup.Builder(this).asCustom(MatrixDescDialog(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.matrix_menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_1 -> {
                matrixDescDialog?.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        if (matrixDescDialog?.isShow == true) {
            matrixDescDialog?.dismiss()
        }
        super.onStop()
    }

    private fun initListener() {

        //平移-Post向右、向下移动
        btnPostTranslate.setOnClickListener {
            iv_matrix.postTranslate(100F, 100F)
        }

        //平移-Pre向左、向上移动
        btnPreTranslate.setOnClickListener {
            iv_matrix.postTranslate(-100F, -100F)
        }

        //缩放
        btnScale.setOnClickListener {

        }

        //扭曲
        btnSkew.setOnClickListener {

        }

        //旋转
        btnRotate.setOnClickListener {

        }

        //重置
        btnReset.setOnClickListener {

        }

        //改变
        btnChange.setOnClickListener {

        }
    }
}