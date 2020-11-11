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

public class DigitalTextView extends AutofitTextView {

    private static String fontPath = "digital.ttf";
    private static Typeface tf;

    public DigitalTextView(Context context) {
        super(context);
        initDigitalTypeFace();
    }

    public DigitalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDigitalTypeFace();
    }

    public DigitalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
