package org.fireking.ap.custom.matix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

public class MartixView extends View {

    private Matrix mMatrix;

    public MartixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("info", "toString=" + mMatrix.toString());
    }
}
