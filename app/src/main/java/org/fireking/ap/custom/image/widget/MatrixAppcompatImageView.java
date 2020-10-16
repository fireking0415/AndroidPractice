package org.fireking.ap.custom.image.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.fireking.ap.R;

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/10/16
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 *
 * @author Wanggang
 */
public class MatrixAppcompatImageView extends View {

    private Matrix mMatrix;
    private Bitmap mBitmap;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MatrixAppcompatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMatrix = new Matrix();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_meizi);
    }

    public void postTranslate(float x, float y) {
        mMatrix.reset();
        mMatrix.postTranslate(x, y);
        postInvalidate();
    }

    public void preTranslate(float x, float y) {
        mMatrix.reset();
        mMatrix.preTranslate(x, y);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    public void postScale(float x, float y) {
        mMatrix.reset();
        mMatrix.postScale(x, y);
        postInvalidate();
    }

    public void preScale(float x, float y) {
        mMatrix.reset();
        mMatrix.preScale(x, y);
        postInvalidate();
    }

    public void postSkew(float x, float y) {
        mMatrix.reset();
        mMatrix.postSkew(x, y);
        postInvalidate();
    }

    public void postRotate(float degree, float px, float py) {
        mMatrix.reset();
        mMatrix.postRotate(degree, px, py);
        postInvalidate();
    }

    public void reset() {
        mMatrix.reset();
        postInvalidate();
    }
}
