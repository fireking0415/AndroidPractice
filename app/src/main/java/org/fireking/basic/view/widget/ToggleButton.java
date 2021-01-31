package org.fireking.basic.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.Checkable;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

public class ToggleButton extends View implements Checkable {


    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint btnPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int width;
    private int height;
    private int outterRadius;
    private int innerRadius;
    private int gap;
    private boolean isChecked;

    private float animationValue;
    private int mBgColorAnimationValue;
    private int mTouchSlop;
    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;
    //两个圆形之间的距离
    private float mCircleOffset;

    private ValueAnimator leftToRightMoveAnimator;
    private ValueAnimator rightToLeftMoveAnimator;

    //点击
    private static final int MODE_DOWN_TAP = 1;
    //拖动按钮
    private static final int MODE_DRAGGER = 2;
    //恢复初始化
    private static final int MODE_RESET = 3;

    private int buttonMode = MODE_RESET;

    public ToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bgPaint.setColor(Color.parseColor("#80AAAAAA"));
        bgPaint.setStyle(Paint.Style.FILL);

        btnPaint.setColor(Color.parseColor("#FFFFFF"));
        btnPaint.setStyle(Paint.Style.FILL);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);
        int height = width / 2;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        outterRadius = height / 2;
        gap = height / 20;
        innerRadius = outterRadius - gap;

        mCircleOffset = outterRadius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(new RectF(0, 0, width, height), outterRadius, outterRadius, bgPaint);

        canvas.drawCircle(outterRadius + animationValue, outterRadius, innerRadius, btnPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                buttonMode = MODE_DOWN_TAP;
                break;
            case MotionEvent.ACTION_MOVE:
                switch (buttonMode) {
                    case MODE_DOWN_TAP:
                        float x = event.getX();
                        float y = event.getY();
                        if ((Math.abs(x - mDownX) > mTouchSlop || Math.abs(y - mDownY) > mTouchSlop)
                                && (Math.abs(x - mDownX) > Math.abs(y - mDownY))) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                            buttonMode = MODE_DRAGGER;
                            mMoveX = x;
                            mMoveY = y;
                            return true;
                        }
                        break;
                    case MODE_DRAGGER:
                        float offsetX = event.getX() - mMoveX;
                        if (isChecked) {
                            dragRightToLeft(offsetX);
                        } else {
                            dragLeftToRight(offsetX);
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (buttonMode) {
                    case MODE_DOWN_TAP:
                        if (isChecked) {
                            doRightToLeftMove(mCircleOffset);
                        } else {
                            doLeftToRightMove(0);
                        }
                        break;
                    case MODE_DRAGGER:
                        if (animationValue > mCircleOffset / 2) {  //向右滚动
                            doLeftToRightMove(animationValue);
                        } else {  //向左滚动
                            doRightToLeftMove(animationValue);
                        }
                        break;
                }
                break;
        }
        return true;
    }

    private void dragLeftToRight(float offsetX) {
        animationValue = offsetX;
        if (offsetX > mCircleOffset) {
            animationValue = mCircleOffset;
        }
        invalidate();
    }

    private void dragRightToLeft(float offsetX) {
        animationValue = offsetX;
        if (offsetX < 0) {
            animationValue = 0;
        }
        invalidate();
    }

    @SuppressLint("RestrictedApi")
    private void doRightToLeftMove(float startOffset) {
        if (rightToLeftMoveAnimator == null) {
            rightToLeftMoveAnimator = ValueAnimator.ofFloat(startOffset, 0);
            rightToLeftMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animationValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            rightToLeftMoveAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isChecked = false;
                    btnPaint.setColor(Color.parseColor("#FFFFFF"));
                    invalidate();
                    buttonMode = MODE_RESET;
                }
            });
            rightToLeftMoveAnimator.setInterpolator(new LinearInterpolator());
            rightToLeftMoveAnimator.setDuration(200);
        }

        if (rightToLeftMoveAnimator.isRunning()) {
            return;
        }
        rightToLeftMoveAnimator.start();

        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(0x806cbbb3, 0x80AAAAAA);
        animator.setEvaluator(ArgbEvaluator.getInstance());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBgColorAnimationValue = (int) animation.getAnimatedValue();
                bgPaint.setColor(mBgColorAnimationValue);
                invalidate();
                buttonMode = MODE_RESET;
            }
        });
        animator.setDuration(200);
        animator.start();
    }

    private void doLeftToRightMove(float startOffset) {
        if (leftToRightMoveAnimator == null) {
            leftToRightMoveAnimator = ValueAnimator.ofFloat(startOffset, mCircleOffset);
            leftToRightMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animationValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            leftToRightMoveAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isChecked = true;
                    btnPaint.setColor(Color.parseColor("#6cbbb3"));
                    invalidate();
                }
            });
            leftToRightMoveAnimator.setInterpolator(new LinearInterpolator());
            leftToRightMoveAnimator.setDuration(200);
        }
        if (leftToRightMoveAnimator.isRunning()) {
            return;
        }
        leftToRightMoveAnimator.start();

        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(0x80AAAAAA, 0x806cbbb3);
        animator.setEvaluator(ArgbEvaluator.getInstance());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBgColorAnimationValue = (int) animation.getAnimatedValue();
                bgPaint.setColor(mBgColorAnimationValue);
                invalidate();
            }
        });
        animator.setDuration(200);
        animator.start();
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        toggle();
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {

    }
}
