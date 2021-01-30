package org.fireking.ap.custom.viewgroup.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import org.fireking.ap.R

class VerifyCodeInputLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var inputReal: EditText
    private lateinit var rvContentList: LinearLayout
    private var onCompleteListener: OnCompleteListener? = null

    private var verifyCodeLen = 0
    private var inputTextSize: Int = 0
    private var inputTextColor: Int = 0
    private var inputBoxSize: Int = 0
    private var verifyInputLayoutHeight = 0
    private var dividerDrawable: Drawable? = null
    private var itemSelector: Int = R.drawable.verify_code_text_selector

    private var inputTextView = ArrayList<TextView>(4)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        LayoutInflater.from(context).inflate(R.layout.verify_code_input_layout, this, true)

        //设置默认值
        verifyCodeLen = 4
        inputTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16.0F, resources.displayMetrics).toInt()
        inputTextColor = Color.parseColor("#FF333333")
        inputBoxSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50F, resources.displayMetrics).toInt()
        dividerDrawable = context.resources.getDrawable(R.drawable.linearlayout_divider)

        //获取自定义属性值
        val a = context.obtainStyledAttributes(attrs, R.styleable.VerifyCodeInputLayout)
        if (a.hasValue(R.styleable.VerifyCodeInputLayout_textSize)) {
            inputTextSize = a.getDimensionPixelSize(R.styleable.VerifyCodeInputLayout_textSize, inputTextSize)
        }

        if (a.hasValue(R.styleable.VerifyCodeInputLayout_textColor)) {
            inputTextColor = a.getColor(R.styleable.VerifyCodeInputLayout_textColor, Color.parseColor("#FF333333"))
        }

        if (a.hasValue(R.styleable.VerifyCodeInputLayout_inputBoxSize)) {
            inputBoxSize = a.getDimensionPixelSize(
                R.styleable.VerifyCodeInputLayout_inputBoxSize,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 44F, resources.displayMetrics).toInt()
            )
        }

        if (a.hasValue(R.styleable.VerifyCodeInputLayout_dividerDrawable)) {
            dividerDrawable = a.getDrawable(R.styleable.VerifyCodeInputLayout_dividerDrawable)
        }

        if (a.hasValue(R.styleable.VerifyCodeInputLayout_itemSelector)) {
            itemSelector = a.getResourceId(R.styleable.VerifyCodeInputLayout_itemSelector, itemSelector)
        }

        if (a.hasValue(R.styleable.VerifyCodeInputLayout_maxLength)) {
            verifyCodeLen = a.getInt(R.styleable.VerifyCodeInputLayout_maxLength, 4)
        }
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        verifyInputLayoutHeight = measuredHeight
    }

    fun setOnCompleteListener(onCompleteListener: OnCompleteListener) {
        this.onCompleteListener = onCompleteListener
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        initContainer()
        initListener()
    }

    private fun initListener() {
        inputReal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setVerifyCodeInputValue(p0.toString())
                if (p0.toString().length == verifyCodeLen) {
                    onCompleteListener?.onComplete(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun setVerifyCodeInputValue(inputText: String) {
        inputTextView.forEach {
            it.text = ""
            it.isSelected = false
        }
        inputTextView.forEachIndexed { index, textView ->
            if (inputText.length > index) {
                textView.isSelected = true
                textView.text = inputText[index].toString()
            }
        }
    }

    private fun initContainer() {

        inputReal = findViewById(R.id.inputReal)
        rvContentList = findViewById(R.id.rvContentList)
        inputReal.width = (dividerDrawable?.minimumWidth ?: 0 * (verifyCodeLen - 1)) + inputBoxSize * verifyCodeLen
        inputReal.height = inputBoxSize
        inputReal.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize * 1.0F)

        inputReal.isCursorVisible = false
        inputReal.filters = arrayOf(InputFilter.LengthFilter(verifyCodeLen))

        inputTextView.clear()

        dividerDrawable?.let {
            it.setBounds(0, 0, it.minimumWidth, it.minimumHeight)
            rvContentList.dividerDrawable = it
        }

        for (i in 0 until verifyCodeLen) {
            val textView = TextView(context)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize * 1.0F)
            textView.width = inputBoxSize
            textView.height = inputBoxSize
            textView.gravity = Gravity.CENTER
            textView.isFocusable = false
            textView.setTextColor(inputTextColor)
            textView.setBackgroundResource(itemSelector)
            inputTextView.add(textView)
        }

        inputTextView.forEach {
            rvContentList.addView(it)
        }
    }

    interface OnCompleteListener {
        fun onComplete(content: String)
    }
}