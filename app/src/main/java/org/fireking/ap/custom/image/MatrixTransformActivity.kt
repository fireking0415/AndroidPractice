package org.fireking.ap.custom.image

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import org.fireking.ap.R
import org.fireking.ap.custom.image.widget.MatrixDescDialog
import org.fireking.ap.databinding.ActivityMatrixTransformBinding
import org.jetbrains.anko.intentFor

class MatrixTransformActivity : AppCompatActivity() {

    private var matrixDescDialog: BasePopupView? = null

    //用来区分是否发生了缩放形变
    private var isScale: Boolean = false

    //用来区分是否发生了旋转形变
    private var isRotate: Boolean = false

    //用来区分是否发生了扭曲(错切)形变
    private var isSkew: Boolean = false

    private var entity: MatrixEntity = MatrixEntity()

    private lateinit var activityMatrixTransformBinding: ActivityMatrixTransformBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MatrixTransformActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMatrixTransformBinding = ActivityMatrixTransformBinding.inflate(layoutInflater)
        setContentView(activityMatrixTransformBinding.root)
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
        activityMatrixTransformBinding.btnPostTranslate.setOnClickListener {
            activityMatrixTransformBinding.ivMatrix.postTranslate(100F, 100F)
        }

        //平移-Pre向左、向上移动
        activityMatrixTransformBinding.btnPreTranslate.setOnClickListener {
            activityMatrixTransformBinding.ivMatrix.postTranslate(-100F, -100F)
        }

        //缩放
        activityMatrixTransformBinding.btnScale.setOnClickListener {
            isScale = if (!isScale) {
                activityMatrixTransformBinding.ivMatrix.postScale(0.5F, 0.5F)
                true
            } else {
                activityMatrixTransformBinding.ivMatrix.postScale(1F, 1F)
                false
            }
        }

        //扭曲
        activityMatrixTransformBinding.btnSkew.setOnClickListener {
            isSkew = if (!isSkew) {
                activityMatrixTransformBinding.ivMatrix.postSkew(0.2F, 0.5F)
                true
            } else {
                activityMatrixTransformBinding.ivMatrix.postSkew(0F, 0F)
                false
            }
        }

        //旋转
        activityMatrixTransformBinding.btnRotate.setOnClickListener {
            isRotate = if (!isRotate) {
                activityMatrixTransformBinding.ivMatrix.postRotate(
                    90F, (activityMatrixTransformBinding.ivMatrix.width / 2).toFloat(),
                    (activityMatrixTransformBinding.ivMatrix.height / 2).toFloat()
                )
                true
            } else {
                activityMatrixTransformBinding.ivMatrix.postRotate(
                    0F, (activityMatrixTransformBinding.ivMatrix.width / 2).toFloat(),
                    (activityMatrixTransformBinding.ivMatrix.height / 2).toFloat()
                )
                false
            }
        }

        //重置
        activityMatrixTransformBinding.btnReset.setOnClickListener {
            activityMatrixTransformBinding.ivMatrix.reset()
        }

        //改变
        activityMatrixTransformBinding.btnChange.setOnClickListener {
            activityMatrixTransformBinding.ivMatrix.updateMatrix(
                entity.value1 ?: "1",
                entity.value2 ?: "0",
                entity.value3 ?: "0",
                entity.value4 ?: "0",
                entity.value5 ?: "1",
                entity.value6 ?: "0",
                entity.value7 ?: "0",
                entity.value8 ?: "0",
                entity.value9 ?: "1"
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}