package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class SinusoidalWaveView extends View {

    private int width;
    private int height;

    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path linePath;

    private double amplitude;

    //角频率， 控制周期
    private double angularFrequency = 8 * 1.0f / 4;
    //初次相位角,
    private double phaseAngle = 0 * Math.PI / 180 + Math.PI / 2 * -1;

    public SinusoidalWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        linePaint.setColor(Color.parseColor("#23a393"));
        linePaint.setStyle(Paint.Style.FILL);

        linePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.width = w;
        this.height = h;

        amplitude = height / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(widthMeasureSpec / 2, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        linePath.reset();

        linePath.moveTo(0, height);
        for (int i = 0; i < width; i++) {
            double angle = i * 1F / width * 2 * Math.PI;
            double y = amplitude * Math.sin(angle * angularFrequency + phaseAngle);
            linePath.lineTo(i, (float) (y + height / 2));
        }
        linePath.lineTo(width, height + 1);
        canvas.drawPath(linePath, linePaint);
    }

    public void setAmplitude(int progress) {
        amplitude = progress * 1.0F / 100 * height / 2;
        invalidate();
    }

    public void setAngularFrequency(int progress) {
        angularFrequency = progress * 1.0F / 4;
        invalidate();
    }

    public void setPhaseAngle(int progress) {
        phaseAngle = progress * Math.PI / 180 + Math.PI / 2 * -1;
        invalidate();
    }
}
