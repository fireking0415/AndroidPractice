package org.fireking.basic.textview.html;

import android.content.Context;
import android.util.TypedValue;

public class DisplayUtil {

    public static int sp2px(Context mContext, int parseInt) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, parseInt,
                mContext.getResources().getDisplayMetrics());
    }
}
