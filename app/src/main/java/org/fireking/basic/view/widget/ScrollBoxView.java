package org.fireking.basic.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class ScrollBoxView extends View {

    private int width, height;

    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_LOADING = 2;
    private static final int TYPE_REVERSE_LOADING = 3;

    private int stateType = TYPE_NORMAL;

    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ovalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ballPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path ovalLeftPath = new Path();
    private Path ovalRightPath = new Path();
    private Path ballPath = new Path();

    private int radius;
    private float ballHeight;
    private int ballMaxRadius;

    private ValueAnimator ovalAnimator;
    private ValueAnimator reverseOvalAnimator;
    private float ovalAnimatorValue;
    private int reverseOvalAnimatorValue;
    private Matrix mMatrix;
    private int currentBallRadius;

    private PathMeasure pathOvalMeasure = new PathMeasure();

    public ScrollBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bgPaint.setColor(Color.parseColor("#207e82"));
        bgPaint.setStrokeWidth(10F);
        bgPaint.setStyle(Paint.Style.STROKE);

        ovalPaint.setColor(Color.parseColor("#FFFFFF"));
        ovalPaint.setStrokeWidth(10F);
        ovalPaint.setStyle(Paint.Style.STROKE);

        ballPaint.setColor(Color.WHITE);
        ballPaint.setStyle(Paint.Style.FILL);
        ballPaint.setStrokeCap(Paint.Cap.ROUND);

        reverseOvalAnimator = ValueAnimator.ofInt(0, 255);
        reverseOvalAnimator.setDuration(4000);
        reverseOvalAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                reverseOvalAnimatorValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        reverseOvalAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                stateType = TYPE_NORMAL;
                ovalAnimator.start();
            }
        });

        ovalAnimator = ValueAnimator.ofFloat(0, 1);
        ovalAnimator.setDuration(4000);
        ovalAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ovalAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        ovalAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                stateType = TYPE_LOADING;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                stateType = TYPE_REVERSE_LOADING;
                reverseOvalAnimator.start();
            }
        });
        ovalAnimator.start();

        mMatrix = new Matrix();
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

        radius = width * 3 / 4 / 2;

        ballHeight = width / 5 * 2;
        ballMaxRadius = radius / 8 / 2;

        currentBallRadius = ballMaxRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-270);

        mMatrix.reset();

        canvas.drawColor(Color.parseColor("#1d5464"));
        ovalLeftPath.addRoundRect(new RectF(-radius, -radius / 2, radius, radius / 2), radius / 2, radius / 2, Path.Direction.CCW);
        ovalRightPath.addRoundRect(new RectF(-radius, -radius / 2, radius, radius / 2), radius / 2, radius / 2, Path.Direction.CW);

        switch (stateType) {
            case TYPE_NORMAL:
                canvas.drawPath(ovalLeftPath, ovalPaint);
                canvas.drawCircle(-ballHeight / 2, 0, ballMaxRadius, ballPaint);
                break;
            case TYPE_LOADING:

                pathOvalMeasure.setPath(ovalLeftPath, false);
                Path dst = new Path();
                pathOvalMeasure.getSegment(pathOvalMeasure.getLength() / 2 * ovalAnimatorValue, pathOvalMeasure.getLength() / 2, dst, true);

                pathOvalMeasure.setPath(ovalRightPath, false);
                Path dst2 = new Path();
                pathOvalMeasure.getSegment(pathOvalMeasure.getLength() / 2 * ovalAnimatorValue, pathOvalMeasure.getLength() / 2, dst2, true);

                canvas.drawPath(ovalLeftPath, bgPaint);
                canvas.drawPath(dst, ovalPaint);
                canvas.drawPath(dst2, ovalPaint);
                if (-ballHeight / 2 + ballHeight * ovalAnimatorValue > ballHeight / 4) {
                    ballPaint.setAlpha((int) (255 - ovalAnimatorValue / 1.5 * 255));
                }
                if (ovalAnimatorValue > 0.99F) {
                    ballPaint.setAlpha((int) (255 - ovalAnimatorValue * 255));
                }
                canvas.drawCircle(-ballHeight / 2 + ballHeight * ovalAnimatorValue, 0, currentBallRadius, ballPaint);
                break;
            case TYPE_REVERSE_LOADING:
                ovalPaint.setAlpha(reverseOvalAnimatorValue);
                canvas.drawPath(ovalLeftPath, ovalPaint);
                ballPaint.setAlpha(reverseOvalAnimatorValue);
                canvas.drawCircle(-ballHeight / 2, 0, ballMaxRadius, ballPaint);
                break;
        }
    }
}
