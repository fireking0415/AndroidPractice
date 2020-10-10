package org.fireking.ap.custom.image.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import org.fireking.ap.R;

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/10/10
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 *
 * @author Wanggang
 */
public class GifImageView extends View {

    private Movie mMovie;
    private float mLeft;
    private float mTop;
    private float mScale;
    private int mMeasuredMovieWidth;
    private int mMeasuredMovieHeight;
    private long mMovieStart;

    public GifImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GifImageView);
        int gifResourceId = -1;
        if (array.hasValue(R.styleable.GifImageView_gif_resource_id)) {
            gifResourceId = array.getResourceId(R.styleable.GifImageView_gif_resource_id, -1);
        }
        array.recycle();
        if (gifResourceId != -1) {
            mMovie = Movie.decodeStream(getResources().openRawResource(gifResourceId));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMovie != null) {
            int movieWidth = mMovie.width();
            int movieHeight = mMovie.height();
            int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
            float scaleW = (float) movieWidth / (float) maximumWidth;
            mScale = 1f / scaleW;
            mMeasuredMovieWidth = maximumWidth;
            mMeasuredMovieHeight = (int) (movieHeight * mScale);
            setMeasuredDimension(mMeasuredMovieWidth, mMeasuredMovieHeight);
        } else {
            setMeasuredDimension(getSuggestedMinimumWidth(),
                    getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mLeft = (getWidth() - mMeasuredMovieWidth) / 2f;
        mTop = (getHeight() - mMeasuredMovieHeight) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        // 如果第一帧，记录起始时间
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        // 取出动画的时长
        int dur = mMovie.duration();
        if (dur == 0) {
            dur = 1000;
        }
        // 算出需要显示第几帧
        canvas.save();
        mMovie.setTime((int) ((now - mMovieStart) % dur));
        //控制canvas缩放比率，将gif放大
        canvas.scale(mScale, mScale);
        mMovie.draw(canvas, mLeft, mTop);
        canvas.restore();
        postInvalidateOnAnimation();
    }
}
