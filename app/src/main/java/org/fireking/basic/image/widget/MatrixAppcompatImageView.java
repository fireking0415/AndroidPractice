package org.fireking.basic.image.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import org.fireking.ap.R;

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

    public void updateMatrix(@NonNull String scaleX, @NonNull String skewX, @NonNull String transX,
                             @NonNull String skewY, @NonNull String scaleY, @NonNull String transY,
                             @NonNull String persp0, @NonNull String persp1, @NonNull String persp2) {
        mMatrix.reset();
        mMatrix.setValues(new float[]{
                Float.parseFloat(scaleX), Float.parseFloat(skewX), Float.parseFloat(transX),
                Float.parseFloat(skewY), Float.parseFloat(scaleY), Float.parseFloat(transY),
                Float.parseFloat(persp0), Float.parseFloat(persp1), Float.parseFloat(persp2)
        });
        postInvalidate();
    }
}
