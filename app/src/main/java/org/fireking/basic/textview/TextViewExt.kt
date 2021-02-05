package org.fireking.basic.textview

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView

fun TextView.setSpannableText(spanList: MutableList<SpanEntity>) {
    val spannableStringBuilder = SpannableStringBuilder()
    var isClickable = false
    spanList.forEach {
        val spannableString = SpannableString(it.content)
        if (it.clickable) {
            spannableString.setSpan(
                ClickSpan(it.content, it.color, it.clickEvent),
                0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            isClickable = true
        } else {
            spannableString.setSpan(
                ForegroundColorSpan(it.color),
                0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        spannableString.setSpan(
            StyleSpan(it.typeface), 0,
            spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringBuilder.append(spannableString)
    }
    this.text = spannableStringBuilder
    if (isClickable) {
        this.highlightColor = Color.TRANSPARENT
        this.setHintTextColor(Color.TRANSPARENT)
        this.movementMethod = LinkMovementMethod.getInstance()
    }
}

class ClickSpan(val content: String, val color: Int, val click: (content: String) -> Unit) :
    ClickableSpan() {
    override fun onClick(widget: View) {
        click.invoke(content)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = color
        ds.isUnderlineText = false
    }
}

data class SpanEntity(
    var color: Int,
    var content: String,
    val typeface: Int = Typeface.NORMAL,
    var clickable: Boolean = false,
    var clickEvent: (content: String) -> Unit = {}
)