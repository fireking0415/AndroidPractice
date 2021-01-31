package org.fireking.basic.view.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class DoubleWaveView extends View {

    private Paint mPaint;
    private Path mPath;
    private int width, height;

    private int dx;

    public DoubleWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPaint.setColor(Color.parseColor("#65ff0000"));
        mPath.moveTo(-width + dx, height / 5 * 4);
        for (int i = 0; i < 3; i++) {
            mPath.rQuadTo(width / 4, -70, width / 2, 0);
            mPath.rQuadTo(width / 4, 70, width / 2, 0);
        }
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        mPath.reset();
        mPaint.setColor(Color.parseColor("#6500ff00"));
        mPath.moveTo(-width + dx, height / 5 * 4);
        for (int i = 0; i < 3; i++) {
            mPath.rQuadTo(width / 4, 70, width / 2, 0);
            mPath.rQuadTo(width / 4, -70, width / 2, 0);
        }
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, width);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
