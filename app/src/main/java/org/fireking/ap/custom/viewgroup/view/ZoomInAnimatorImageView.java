package org.fireking.ap.custom.viewgroup.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

/**
 * 不间断放大缩小动画<p>
 *
 * @author wanggang
 * @since 2019/11/12 22:52
 */
public class ZoomInAnimatorImageView extends RCImageView {

    private ValueAnimator mValueAnimator;

    public ZoomInAnimatorImageView(Context context) {
        this(context, null);
    }

    public ZoomInAnimatorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomInAnimatorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mValueAnimator = ValueAnimator.ofFloat(0.9F, 1F, 0.9F);
        mValueAnimator.setDuration(1500);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setScaleX((Float) animation.getAnimatedValue());
                setScaleY((Float) animation.getAnimatedValue());
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mValueAnimator != null && !mValueAnimator.isRunning()) {
            mValueAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.end();
        }
    }
}
