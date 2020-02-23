package org.fireking.ap.custom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

public class LineChatView extends View {

    private int width, height;
    private Paint guidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int padding = 24;
    private int textRectWidth = 60;

    private int[] vData = {0, 20, 40, 60, 80, 100, 120, 140};
    private int[] hData = {2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017};
    private LineChatData[] paintDatas = {
            new LineChatData(2009, 20),
            new LineChatData(2010, 30),
            new LineChatData(2011, 85),
            new LineChatData(2012, 40),
            new LineChatData(2013, 60),
            new LineChatData(2014, 75),
            new LineChatData(2015, 135),
            new LineChatData(2016, 140),
            new LineChatData(2017, 90)
    };

    public LineChatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        linePaint.setStrokeWidth(3);
        linePaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.STROKE);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setTextSize(24);

        guidePaint.setStrokeWidth(1.5F);
        guidePaint.setColor(Color.parseColor("#80FFFFFF"));
        guidePaint.setStyle(Paint.Style.STROKE);

        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setStrokeWidth(12);
        dotPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("info", w + "," + h);
        width = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(widthSize * 3 / 4, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数学坐标系
        canvas.drawColor(Color.parseColor("#F14400"));

        //绘制坐标系
        canvas.drawLine(padding + textRectWidth, height - textRectWidth - padding,
                width - padding, height - textRectWidth - padding, linePaint);
        canvas.drawLine(padding + textRectWidth, height - textRectWidth - padding,
                padding + textRectWidth, padding, linePaint);

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

        //绘制折线
        Path linePath = new Path();
        int paintMaxHeight = height - textRectWidth - 2 * padding;
        for (int i = 0; i < paintDatas.length; i++) {
            if (i == 0) {
                linePath.moveTo(paintWidth * i, -paintDatas[i].value * (paintMaxHeight / 140));
            }
            linePath.lineTo(paintWidth * i, -paintDatas[i].value * (paintMaxHeight / 140));
        }
        canvas.drawPath(linePath, linePaint);

        //绘制数据折线点
        for (int i = 0; i < paintDatas.length; i++) {
            canvas.drawPoint(paintWidth * i, -paintDatas[i].value * (paintMaxHeight / 140), dotPaint);
        }
    }

    public static class LineChatData {
        private int year;
        private int value;

        public LineChatData(int year, int value) {
            this.year = year;
            this.value = value;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }
    }
}
