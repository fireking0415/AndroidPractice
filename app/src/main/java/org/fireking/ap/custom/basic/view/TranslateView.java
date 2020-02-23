package org.fireking.ap.custom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class TranslateView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public TranslateView(Context context) {
        super(context);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GREEN);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        canvas.translate(200, 200);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 100, mPaint);
    }
}
