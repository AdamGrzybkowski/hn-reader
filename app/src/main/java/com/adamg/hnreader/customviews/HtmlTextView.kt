package com.adamg.hnreader.customviews

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.widget.TextView

class HtmlTextView: TextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @Suppress("DEPRECATION")
    override fun setText(text: CharSequence?, type: BufferType?) {
        var parsedText: Spanned
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            parsedText = Html.fromHtml(text.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            parsedText = Html.fromHtml(text.toString())
        }
        super.setMovementMethod(LinkMovementMethod.getInstance())
        super.setText(parsedText, type)
        super.setTextIsSelectable(true)
    }



}