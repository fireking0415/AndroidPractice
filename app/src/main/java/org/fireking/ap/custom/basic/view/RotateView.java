package org.fireking.ap.custom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class RotateView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public RotateView(Context context) {
        super(context);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);

        //设置属性
        paint.setStrokeWidth(4F);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        //绘制两个嵌套的圆
        canvas.drawCircle(0, 0, 400, paint);
        canvas.drawCircle(0, 0, 350, paint);

        //绘制两个圆之间的分割线
        for (int i = 0; i < 360; i++) {
            canvas.drawLine(0, 400, 0, 350, paint);
            canvas.rotate(10);
        }
    }
}
