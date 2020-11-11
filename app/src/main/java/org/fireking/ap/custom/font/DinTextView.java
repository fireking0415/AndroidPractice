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

public class DinTextView extends AutofitTextView {

    private static String fontPath = "DIN-Alternate.ttf";
    private static volatile Typeface tf;

    public DinTextView(Context context) {
        super(context);
        initDigitalTypeFace();
    }

    public DinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDigitalTypeFace();
    }

    public DinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDigitalTypeFace();
    }

    private synchronized void initDigitalTypeFace() {
        if (tf == null) {
            tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);
        }
        this.setTypeface(tf);
    }

    public synchronized static Typeface getDigitalTypeFace(Context context){
        if(tf == null){
            tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        }
        return tf;
    }
}
