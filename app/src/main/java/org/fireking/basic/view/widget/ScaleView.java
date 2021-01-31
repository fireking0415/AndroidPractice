package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class ScaleView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public ScaleView(Context context) {
        super(context);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
//        RectF rectF = new RectF(0, -400, 400, 0);
//
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.scale(-0.5F, -0.5F);
//
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2F);

        RectF rectF = new RectF(-400, -400, 400, 400);
        for (int i = 0; i < 120; i++) {

            canvas.scale(0.9F, 0.9F);
            canvas.drawRect(rectF, mPaint);
        }
    }
}
