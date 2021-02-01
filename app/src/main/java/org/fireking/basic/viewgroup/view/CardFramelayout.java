package org.fireking.basic.viewgroup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CardFramelayout extends View {

    private Paint mShaderPaint;
    private Paint mStrokePaint;
    private Paint mContentPaint;
    private Paint mBgPaint;

    private int mColorStart = Color.parseColor("#37000000");
    private int mColorEnd = Color.parseColor("#03000000");
    private int mColorHorizentalStart = Color.parseColor("#eeeeee");
    private int mColorHorizentalEnd = Color.parseColor("#ffffffff");
    private int[] mColors = new int[]{Color.parseColor("#eeeeee"), Color.parseColor("#efefef"), Color.parseColor("#f0f0f0"),
            Color.parseColor("#f1f1f1"), Color.parseColor("#f2f2f2"), Color.parseColor("#f3f3f3"),
            Color.parseColor("#f4f4f4"), Color.parseColor("#f5f5f5"), Color.parseColor("#f6f6f6"),
            Color.parseColor("#f7f7f7"), Color.parseColor("#f8f8f8"), Color.parseColor("#f9f9f9"),
            Color.parseColor("#fefefe")};


    private float[] mLocations;

    private int offset = 40;
    private int radius = 60;
    private int mWidth;
    private int mHeight;
    private Rect mRect, mContentRect, mShaderRect, mLeftRect, mRightRect;
    private LinearGradient mLinearGradient, mLeftGradient, mRightGradient;

    private Rect mRectLeftBottom;
    private Paint mLeftBottomPaint, mLeftPaint;
    private RadialGradient mLeftBottomGradient;

    public CardFramelayout(@NonNull Context context) {
        super(context);
        init();
    }

    public CardFramelayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardFramelayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mShaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShaderPaint.setStyle(Paint.Style.FILL);
        mShaderPaint.setColor(Color.BLUE);

        mLeftPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftPaint.setStyle(Paint.Style.FILL);
        mLeftPaint.setColor(Color.BLUE);

        mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContentPaint.setStyle(Paint.Style.FILL);
        mContentPaint.setColor(Color.WHITE);

        mLeftBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftBottomPaint.setStyle(Paint.Style.FILL);
        mLeftBottomPaint.setColor(Color.WHITE);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(Color.parseColor("#eeeeee"));

        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(Color.RED); // mColors[0]
        mStrokePaint.setStrokeWidth(3);

        mLocations = new float[mColors.length];
        float step = 1.0f / mColors.length;
        for (int i = 0; i < mColors.length; i++) {
            mLocations[i] = step * i;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        int bigRadius = offset + radius;
        int centerX = bigRadius;
        int centerY = mHeight - bigRadius;

        mRect = new Rect(0, 0, w, h);
        mContentRect = new Rect(offset, offset, w - offset, h - offset);
        mShaderRect = new Rect(centerX, h - offset, w, h);

        mLeftRect = new Rect(0, offset, offset, centerY);

        int contentWidth = w - offset * 2;
        mRightRect = new Rect(offset + contentWidth, offset, w, h - offset);

        // 绘制线性渐变，bottom的
        mLinearGradient = new LinearGradient(offset, h - offset, offset, h, new int[]{mColorStart, mColorEnd}, new float[]{0, 1}, Shader.TileMode.CLAMP);
//        mLeftGradient       = new LinearGradient(0, offset, offset, offset, new int[]{mColorHorizentalEnd, mColorStart}, new float[] {0, 1}, Shader.TileMode.CLAMP);
//        mRightGradient      = new LinearGradient(offset + contentWidth, offset, w, offset, new int[]{mColorStart, mColorHorizentalEnd}, new float[] {0, 1}, Shader.TileMode.CLAMP);
        // 绘制左边的线性渐变
        mLeftGradient = new LinearGradient(0, offset, offset, offset, new int[]{mColorEnd, mColorStart}, new float[]{0, 1}, Shader.TileMode.CLAMP);
//        mLeftGradient       = new LinearGradient(0, offset, offset, offset, new int[]{mColorStart, mColorEnd}, new float[] {0, 1}, Shader.TileMode.CLAMP);
        mRightGradient = new LinearGradient(offset + contentWidth, offset, w, offset, new int[]{mColorHorizentalStart, mColorHorizentalEnd}, new float[]{0, 1}, Shader.TileMode.CLAMP);
//        mLinearGradient = new LinearGradient(0, h - offset, 0, h, mColors, mLocations, Shader.TileMode.CLAMP);
        mLeftPaint.setShader(mLeftGradient);
        mShaderPaint.setShader(mLinearGradient);

        mRectLeftBottom = new Rect(0, h - offset * 2, offset * 2, h);

//        int bigRadius = offset + radius;
//        int centerX = bigRadius;
//        int centerY = mHeight - bigRadius;

//        mLeftBottomGradient = new RadialGradient(offset , h - offset, offset, new int[]{Color.RED, Color.GREEN}, new float[] {0, 1}, Shader.TileMode.CLAMP);
        // 这个比较核心，当绘制弧形渐变的时候，要设置两个圆形的比例，再绘制圆形就行了。
        float startRatio = radius * 1.0f / bigRadius;
        mLeftBottomGradient = new RadialGradient(centerX, centerY, bigRadius, new int[]{Color.TRANSPARENT, mColorStart, mColorEnd}, new float[]{0, startRatio, 1}, Shader.TileMode.CLAMP);
//        mLeftBottomGradient = new RadialGradient(offset , h - offset, offset, new int[]{mColorStart, mColorEnd}, new float[] {0, 1}, Shader.TileMode.CLAMP);
        mLeftBottomPaint.setShader(mLeftBottomGradient);
    }

    Path path = new Path();
    Path shaderPath = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(0, 0, mWidth, mHeight, mBgPaint);

//        canvas.drawRect(mRectLeftBottom, mLeftBottomPaint);

//        canvas.drawRect(mContentRect, mContentPaint);

        mShaderPaint.setShader(mLinearGradient);
        canvas.drawRect(mShaderRect, mShaderPaint);


        canvas.drawRect(mLeftRect, mLeftPaint);


        int bigRadius = offset + radius;
        int centerX = bigRadius;
        int centerY = mHeight - bigRadius;
        path.moveTo(centerX, centerY);

        // 绘制弧形 center (bigRadius, mHeight - bigRadius);
        RectF arc = new RectF();
        int l = bigRadius - radius;
        int t = mHeight - bigRadius - radius;
        int r = bigRadius + radius;
        int b = mHeight - bigRadius + radius;
        arc.set(l, t, r, b);
//        canvas.drawArc(arc, 90, 90, true, mStrokePaint);

//        shaderPath.setFillType(Path.FillType.EVEN_ODD);
        shaderPath.reset();
        shaderPath.moveTo(0, centerY);
        shaderPath.lineTo(offset, centerY);

//        mStrokePaint.setStrokeWidth(20);
//        canvas.drawPoint(offset, mHeight - centerY, mStrokePaint);
//        mStrokePaint.setStrokeWidth(4);

        shaderPath.addArc(arc, 180, -90);

        shaderPath.moveTo(centerX, mHeight - offset);
        shaderPath.lineTo(centerX, mHeight);

        RectF outRect = new RectF();
        outRect.set(centerX - bigRadius, centerY - bigRadius, centerX + bigRadius, centerY + bigRadius);
        shaderPath.addArc(outRect, 90, 90);
        shaderPath.moveTo(offset, centerY);
        shaderPath.close();
//        mStrokePaint.setShader(mLeftBottomGradient);
//        canvas.drawPath(shaderPath, mStrokePaint); // mLeftBottomPaint   mStrokePaint

        RectF circle = new RectF();
        circle.set(0, mHeight - centerY * 2, centerY * 2, mHeight);
        canvas.drawArc(outRect, 90, 90, true, mLeftBottomPaint);

//        mShaderPaint.setShader(mRightGradient);
//        canvas.drawRect(mRightRect, mShaderPaint);

    }
}