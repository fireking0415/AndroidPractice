package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class CircleColoraView extends View {

    private int width;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private SweepGradient gradient;

    public CircleColoraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w * 4 / 6 / 2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1, -1);

        gradient = new SweepGradient(0, 0, new int[]{
                Color.parseColor("#f2f4b2"),
                Color.parseColor("#cce490"),
                Color.parseColor("#0c907d"),
                Color.parseColor("#0d627a"),
        }, null);

        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //绘制辅助线
        canvas.drawLine(0, -getHeight(), 0, getHeight(), linePaint);
        canvas.drawLine(-getWidth(), 0, getWidth(), 0, linePaint);

        canvas.rotate(-60);
        paint.setShader(gradient);

        Path path = new Path();
        path.addArc(new RectF(-width, -width, width, width), 0, 300);

        PathMeasure measure = new PathMeasure(path, false);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{measure.getLength() / 60, measure.getLength() / 60}, 0);
        paint.setPathEffect(dashPathEffect);

        canvas.drawPath(path, paint);
    }
}
