package org.fireking.basic.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 蜘蛛网
 */
public class SpiderView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint spiderPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Path path = new Path();
    private Path spiderPath = new Path();
    private Rect textRect = new Rect();
    private int radius;
    private int gridCount = 6;
    private final List<FloatPoint> points = new ArrayList<>();
    private String[] pointLetters;
    private int[] pointProgress;

    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public void setData(List<SpiderData> dataList) {
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        pointLetters = new String[dataList.size()];
        pointProgress = new int[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            pointLetters[i] = dataList.get(i).label;
            pointProgress[i] = dataList.get(i).value;
        }
        invalidate();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#999999"));
        paint.setStrokeWidth(2);
        points.clear();

        spiderPaint.setStyle(Paint.Style.FILL);
        spiderPaint.setColor(Color.parseColor("#8098ccd3"));

        textPaint.setColor(Color.parseColor("#F14400"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = w / 6 * 4 / 2;

        //设置绘制文字大小
        textPaint.setTextSize(40 * (radius / 400F));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        spiderPath.reset();
        points.clear();

        if (pointProgress == null || pointLetters.length == 0) {
            return;
        }

        //移动中心点
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //调整y轴方向
        canvas.scale(1, -1);

        for (int k = 0; k < 6; k++) {
            final int dynRadius = (radius - radius * k / 6);
            path.moveTo(dynRadius, 0);
            if (k == 0) {
                points.add(new FloatPoint(dynRadius, 0));
            }
            for (int i = 1; i < gridCount; i++) {
                final float x = (float) (Math.cos(Math.toRadians(60 * i)) * dynRadius);
                final float y = (float) (Math.sin(Math.toRadians(60 * i)) * dynRadius);
                path.lineTo(x, y);
                if (k == 0) {
                    points.add(new FloatPoint(x, y));
                }
            }
            path.close();
        }
        canvas.drawPath(path, paint);

        canvas.scale(1, -1);
        //绘制链接圆心的线
        for (int i = 0; i < points.size(); i++) {
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(points.get(i).x, points.get(i).y, 0, 0, paint);
        }

        //绘制文字
        canvas.drawText(pointLetters[0], radius + 40 * (radius / 400F), 0, textPaint);
        for (int i = 1; i < gridCount; i++) {
            final float x = (float) (Math.cos(Math.toRadians(60 * i)) * (radius + 40 * (radius / 400F)));
            final float y = (float) (Math.sin(Math.toRadians(60 * i)) * (radius + 40 * (radius / 400F)));
            textPaint.getTextBounds(pointLetters[0], 0, pointLetters[0].length(), textRect);

            if (y < 0) {
                canvas.drawText(pointLetters[i], x - textRect.width(), y, textPaint);
            } else {
                canvas.drawText(pointLetters[i], x - textRect.width(), y + textRect.height(), textPaint);
            }
        }

        //绘制占比
        spiderPath.moveTo(pointProgress[0], 0);
        for (int i = 1; i < pointProgress.length; i++) {
            final float x = (float) (Math.cos(Math.toRadians(60 * i)) * pointProgress[i]);
            final float y = (float) (Math.sin(Math.toRadians(60 * i)) * pointProgress[i]);
            spiderPath.lineTo(x, y);
        }
        spiderPath.close();
        canvas.drawPath(spiderPath, spiderPaint);
    }

    public void setSpiderColor(int color) {
        spiderPaint.setColor(color);
        invalidate();
    }

    public void setLetterColor(int color) {
        textPaint.setColor(color);
        invalidate();
    }

    public void setGridColor(int color) {
        paint.setColor(color);
        invalidate();
    }

    private static class FloatPoint {

        private float x;
        private float y;

        FloatPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }

    public static class SpiderData {
        private int value;
        private String label;

        public SpiderData(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }

}
