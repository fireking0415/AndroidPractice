package org.fireking.ap.custom.textview;

/**
 * @author liuj
 * @created on 2018/12/11
 * @desc desc
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import me.grantland.widget.AutofitTextView;

public class DinBoldTextView extends AutofitTextView {

    private static String fontPath = "DIN-Alternate-Bold.ttf";
    private static volatile Typeface tf;

    public DinBoldTextView(Context context) {
        super(context);
        initDigitalTypeFace();
    }

    public DinBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDigitalTypeFace();
    }

    public DinBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
