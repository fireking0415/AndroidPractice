package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class HistogramView extends View {

    private int width, height;
    private Paint guidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int padding = 24;
    private int textRectWidth = 60;

    private int[] vData = {0, 20, 40, 60, 80, 100, 120, 140};
    private int[] hData = {2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017};
    private LineChatData[] paintDatas = {
            new LineChatData(2009, 20, 80),
            new LineChatData(2010, 40, 10),
            new LineChatData(2011, 45, 75),
            new LineChatData(2012, 40, 15),
            new LineChatData(2013, 60, 28),
            new LineChatData(2014, 75, 25),
            new LineChatData(2015, 105, 25),
            new LineChatData(2016, 30, 20),
            new LineChatData(2017, 90, 5)
    };

    private SweepGradient gradient;

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        linePaint.setStrokeWidth(3);
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setTextSize(24);

        guidePaint.setStrokeWidth(1.5F);
        guidePaint.setColor(Color.parseColor("#80000000"));
        guidePaint.setStyle(Paint.Style.STROKE);

        rectPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(widthSize * 3 / 4, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#ebebe3"));

        //绘制坐标系
        canvas.drawLine(padding + textRectWidth, height - textRectWidth - padding, width - padding, height - textRectWidth - padding, linePaint);
        canvas.drawLine(padding + textRectWidth, height - textRectWidth - padding, padding + textRectWidth, padding, linePaint);

        //移动坐标系为当前的绘制坐标系
        canvas.translate(padding + textRectWidth, height - textRectWidth - padding);

        //绘制文字
        int paintHeight = (height - textRectWidth - 2 * padding) / vData.length;
        for (int i = 0; i < vData.length; i++) {
            canvas.drawText(String.valueOf(vData[i]), -textRectWidth, -paintHeight * i, paint);
        }

        int paintWidth = (width - 2 * padding - textRectWidth) / hData.length;
        for (int i = 0; i < hData.length; i++) {
            canvas.drawText(String.valueOf(hData[i]), paintWidth * i, textRectWidth / 3 * 2, paint);
        }

        //绘制分栏辅助线
        for (int i = 1; i <= vData.length; i++) {
            canvas.drawLine(0, -paintHeight * i, width - 2 * padding - textRectWidth, -paintHeight * i, guidePaint);
        }

        //绘制颜色
        int paintMaxHeight = height - textRectWidth - 2 * padding;

        for (int i = 0; i < paintDatas.length; i++) {
            Path path = new Path();
            gradient = new SweepGradient(paintWidth * i, -(paintDatas[i].value) * (paintMaxHeight / 140), new int[]{
                    Color.parseColor("#207e82"),
                    Color.parseColor("#f05d23"),
            }, null);
            rectPaint.setShader(gradient);
            path.addRect(new RectF(paintWidth * i, -(paintDatas[i].value + paintDatas[i].value2) * (paintMaxHeight / 140), paintWidth * i + 40, 0), Path.Direction.CW);
            canvas.drawPath(path, rectPaint);
        }
    }

    public static class LineChatData {
        private int year;
        private int value;
        private int value2;

        public LineChatData(int year, int value, int value2) {
            this.year = year;
            this.value = value;
            this.value2 = value2;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        public int getValue2() {
            return value2;
        }
    }
}
