package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class SkewView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public SkewView(Context context) {
        super(context);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4F);

        //移动中心点
        canvas.translate(getWidth() / 2, getHeight() / 2);

        //绘制一个矩形
        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawRect(rectF, paint);

        //进行skew错切
        canvas.skew(1, 0);  //取tan值
        canvas.drawRect(rectF, paint);
    }
}
