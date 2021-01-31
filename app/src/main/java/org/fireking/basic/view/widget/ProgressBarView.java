package org.fireking.basic.view.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import org.fireking.ap.R;

public class ProgressBarView extends View {

    private static final int DEFAULT_MAX_PROGRESS = 100;

    private int maxProgress;
    private int currentProgress;

    private int progressHeight;
    private int progressWidth;
    private int paddingWidth;
    private int circleRadius;

    private static final String LOADING = "Loading...";

    private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint circleOvalPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint fengchePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    private ValueAnimator fengcheAnimator;
    private float fengcheRadiusValue;
    private float padding;
    private Paint mBitmapPaint;

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        maxProgress = DEFAULT_MAX_PROGRESS;
        padding = 25F;

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setFilterBitmap(true);

        backgroundPaint.setColor(Color.parseColor("#FBE089"));
        backgroundPaint.setStyle(Paint.Style.FILL);

        fengchePaint.setColor(Color.parseColor("#FFFFFF"));
        fengchePaint.setStrokeWidth(8);
        fengchePaint.setStyle(Paint.Style.STROKE);

        circleOvalPaint.setColor(Color.parseColor("#FBC53F"));

        progressPaint.setColor(Color.parseColor("#FEC009"));
        progressPaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.WHITE);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Arimo.ttf");
        textPaint.setTypeface(typeface);
        textPaint.setTextSize(60F);

        fengcheAnimator = ValueAnimator.ofFloat(0, 359.9F);
        fengcheAnimator.setDuration(1200);
        fengcheAnimator.setRepeatCount(-1);
        fengcheAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fengcheRadiusValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        fengcheAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(widthMeasureSpec / 4 * 3, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        progressHeight = h / 4;
        progressWidth = w / 4 * 3;

        paddingWidth = (w - progressWidth) / 2;
        circleRadius = progressHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制进度条背景，由一个半圆 + 一个矩形 + 一个风车 + 风车下面的圆组成

        //1-绘制半圆
        canvas.save();
        canvas.translate(circleRadius + paddingWidth, getHeight() / 2);
        canvas.drawArc(new RectF(-circleRadius, -circleRadius, circleRadius, circleRadius), 90, 180, true, backgroundPaint);
        canvas.restore();

        //2-绘制矩形
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawRect(new RectF(-progressWidth / 2 + circleRadius, -progressHeight / 2, progressWidth / 2 - circleRadius, progressHeight / 2), backgroundPaint);
        canvas.restore();


        //绘制文字
        Rect rect = new Rect();
        textPaint.getTextBounds(LOADING, 0, LOADING.length(), rect);
        canvas.drawText(LOADING, getWidth() / 2 - rect.width() / 2, getHeight() / 3 - rect.height(), textPaint);

        //绘制进度,进度分为半圆时候的进度和矩形时候的进度，需要分别绘制

        float progressForWith = (progressWidth - circleRadius) * 1.00F / maxProgress * currentProgress;

        //绘制树叶效果
        drawLeaf(canvas);

        float progressCircleRadius = circleRadius - padding;
        if (progressForWith <= circleRadius) {
            //1-绘制半圆进度
            canvas.save();
            canvas.translate(progressCircleRadius + paddingWidth + padding, getHeight() / 2);
            canvas.rotate(90);
            float sweepAngle = (float) Math.toDegrees(Math.acos((progressCircleRadius - progressForWith) / progressCircleRadius));
            float startRadius = 90 - sweepAngle;
            canvas.drawArc(new RectF(-progressCircleRadius, -progressCircleRadius, progressCircleRadius, progressCircleRadius), startRadius, sweepAngle * 2, false, progressPaint);
            canvas.restore();
        } else {
            //2-绘制矩形进度
            canvas.save();
            canvas.translate(progressCircleRadius + paddingWidth + padding, getHeight() / 2);
            canvas.drawArc(new RectF(-progressCircleRadius, -progressCircleRadius, progressCircleRadius, progressCircleRadius), 90, 180, false, progressPaint);

            canvas.drawRect(new RectF(0, -progressHeight / 2 + padding, progressForWith - progressCircleRadius, progressHeight / 2 - padding), progressPaint);
            canvas.restore();
        }

        //3-绘制风车  + 风车背景
        canvas.save();
        canvas.translate(progressWidth + circleRadius / 2, getHeight() / 2);
        canvas.rotate(-fengcheRadiusValue);
        canvas.drawCircle(0, 0, circleRadius, fengchePaint);
        canvas.drawCircle(0, 0, circleRadius, circleOvalPaint);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fengshan);
//        Matrix matrix = new Matrix();
//        Bitmap dstBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        canvas.drawBitmap(dstBitmap, -circleRadius + (circleRadius - bitmap.getWidth() / 2), -circleRadius + (circleRadius - bitmap.getHeight() / 2), null);
//        canvas.restore();

        //绘制当前进度
        String currentProgressStr = "当前进度 " + currentProgress + " %";
        rect = new Rect();
        textPaint.getTextBounds(currentProgressStr, 0, currentProgressStr.length(), rect);
        canvas.drawText(currentProgressStr, getWidth() / 2 - rect.width() / 2, getHeight() / 5 * 4, textPaint);
    }

    private void drawLeaf(Canvas canvas) {
        canvas.save();

        canvas.restore();
    }

    private class PointD {

        private double x;
        private double y;

        public PointD(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        invalidate();
    }

    public void setProgress(int progress) {
        this.currentProgress = progress;
        invalidate();
    }
}
