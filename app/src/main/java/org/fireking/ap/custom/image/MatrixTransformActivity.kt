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

    //用来区分是否发生了缩放形变
    private var isScale: Boolean = false

    //用来区分是否发生了旋转形变
    private var isRotate: Boolean = false

    //用来区分是否发生了扭曲(错切)形变
    private var isSkew: Boolean = false

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
            isScale = if (!isScale) {
                iv_matrix.postScale(0.5F, 0.5F)
                true
            } else {
                iv_matrix.postScale(1F, 1F)
                false
            }
        }

        //扭曲
        btnSkew.setOnClickListener {
            isSkew = if (!isSkew) {
                iv_matrix.postSkew(0.2F, 0.5F)
                true
            } else {
                iv_matrix.postSkew(0F, 0F)
                false
            }
        }

        //旋转
        btnRotate.setOnClickListener {
            isRotate = if (!isRotate) {
                iv_matrix.postRotate(
                    90F, (iv_matrix.width / 2).toFloat(),
                    (iv_matrix.height / 2).toFloat()
                )
                true
            } else {
                iv_matrix.postRotate(
                    0F, (iv_matrix.width / 2).toFloat(),
                    (iv_matrix.height / 2).toFloat()
                )
                false
            }
        }

        //重置
        btnReset.setOnClickListener {
            iv_matrix.reset()
        }

        //改变
        btnChange.setOnClickListener {

        }
    }
}