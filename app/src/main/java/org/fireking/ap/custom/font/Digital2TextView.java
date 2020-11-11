package org.fireking.ap.custom.font;

/**
 * @author liuj
 * @created on 2018/12/11
 * @desc desc
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import me.grantland.widget.AutofitTextView;

public class Digital2TextView extends AutofitTextView {

    private static String fontPath = "digital2.ttf";
    private static Typeface tf;

    public Digital2TextView(Context context) {
        super(context);
        initDigitalTypeFace();
    }

    public Digital2TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDigitalTypeFace();
    }

    public Digital2TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDigitalTypeFace();
    }

    private void initDigitalTypeFace() {
        if (tf == null) {
            tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        }
        this.setTypeface(tf);
    }
}
