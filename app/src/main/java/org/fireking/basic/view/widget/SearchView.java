package org.fireking.basic.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SearchView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path searchPath;
    private Path circlePath;
    private PathMeasure pathMeasure;

    private float searchAnimationValue = 0F;
    private float circleAnimationValue = 0F;
    private float reverseSearchAnimationValue = 0F;

    private ValueAnimator reverseSearchAnimator;
    private ValueAnimator circleAnimator;
    private ValueAnimator searchAnimator;

    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_PRE_LOADING = 2;
    private static final int TYPE_LOADING = 3;
    private static final int TYPE_RESET_NORMAL = 4;

    private int currentType = TYPE_NORMAL;
    private int width;
    private int height;
    private int radius;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setStrokeWidth(18);
        paint.setStrokeCap(Paint.Cap.ROUND);

        reverseSearchAnimator = ValueAnimator.ofFloat(1, 0);
        reverseSearchAnimator.setDuration(2000);
        reverseSearchAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                reverseSearchAnimationValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        reverseSearchAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentType = TYPE_NORMAL;
                searchAnimator.start();
            }
        });

        circleAnimator = ValueAnimator.ofFloat(0, 1);
        circleAnimator.setDuration(2000);
        circleAnimator.setRepeatCount(3);
        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleAnimationValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        circleAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                currentType = TYPE_RESET_NORMAL;
                reverseSearchAnimator.start();
            }
        });

        searchAnimator = ValueAnimator.ofFloat(0, 1);
        searchAnimator.setDuration(2000);
        searchAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                searchAnimationValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        searchAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                currentType = TYPE_PRE_LOADING;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                currentType = TYPE_LOADING;
                circleAnimator.start();
            }
        });

        searchAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = width / 4 * 3;
    }

    public void startAnimator() {
        searchAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#db2d43"));
        canvas.translate(getWidth() / 2, getHeight() / 2);

        pathMeasure = new PathMeasure();

        //绘制放大镜
        searchPath = new Path();
        searchPath.addArc(new RectF(-radius / 4, -radius / 4, radius / 4, radius / 4), 45, 359.9F);

        circlePath = new Path();
        circlePath.addArc(new RectF(-radius / 2, -radius / 2, radius / 2, radius / 2), 45, -359.9F);

        pathMeasure.setPath(circlePath, false);

        float[] pos = new float[2];
        float[] tan = new float[2];

        pathMeasure.getPosTan(0, pos, tan);

        searchPath.lineTo(pos[0], pos[1]);

        switch (currentType) {
            case TYPE_NORMAL:
                canvas.drawPath(searchPath, paint);
                break;
            case TYPE_PRE_LOADING:
                Path dst = new Path();
                pathMeasure.setPath(searchPath, false);
                pathMeasure.getSegment(pathMeasure.getLength() * searchAnimationValue, pathMeasure.getLength(), dst, true);
                canvas.drawPath(dst, paint);
                break;
            case TYPE_LOADING:
                Path dst2 = new Path();
                pathMeasure.setPath(circlePath, false);
                float stop = pathMeasure.getLength() * circleAnimationValue;
                float start = (float) (stop - ((0.5 - Math.abs(circleAnimationValue - 0.5)) * getWidth()));
                pathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, paint);
                break;
            case TYPE_RESET_NORMAL:
                Path dst3 = new Path();
                pathMeasure.setPath(searchPath, false);
                pathMeasure.getSegment(pathMeasure.getLength() * reverseSearchAnimationValue, pathMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, paint);
                break;
        }
    }
}