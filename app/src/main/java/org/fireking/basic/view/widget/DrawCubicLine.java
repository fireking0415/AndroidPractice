package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawCubicLine extends View {
    public DrawCubicLine(Context context) {
        this(context, null);
    }

    public DrawCubicLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCubicLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, new Paint());
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), new Paint());

        canvas.drawRect(getWidth() / 2 - 50, getHeight() / 2 - 500,
                getWidth() / 2 + 50, getHeight() / 2 + 500, new Paint());

        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(4);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.parseColor("#F14400"));

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 50, getHeight() / 2 - 500);
        path.cubicTo(getWidth() / 2 - 50, getHeight() / 2 - 500,
                getWidth() / 2, getHeight() / 2 - 450,
                getWidth() / 2, getHeight() / 2 - 300
        );
        path.cubicTo(getWidth() / 2, getHeight() / 2 + 300,
                getWidth() / 2, getHeight() / 2 + 450,
                getWidth() / 2 + 50, getHeight() / 2 + 500
        );
        canvas.drawPath(path, linePaint);
    }
}
