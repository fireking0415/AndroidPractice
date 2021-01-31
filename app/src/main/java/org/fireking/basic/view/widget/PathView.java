package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class PathView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path src = new Path();
        Path dist = new Path();

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4F);

        //绘制辅助线
        Path linePath = new Path();
        linePath.moveTo(0, getHeight() / 2);
        linePath.lineTo(getWidth(), getHeight() / 2);
        linePath.lineTo(getWidth() - 40, getHeight() / 2 - 40);
        linePath.moveTo(getWidth(), getHeight() / 2);
        linePath.lineTo(getWidth() - 40, getHeight() / 2 + 40);

        linePath.moveTo(getWidth() / 2, 0);
        linePath.lineTo(getWidth() / 2, getHeight());
        linePath.lineTo(getWidth() / 2 - 40, getHeight() - 40);
        linePath.moveTo(getWidth() / 2, getHeight());
        linePath.lineTo(getWidth() / 2 + 40, getHeight() - 40);
        canvas.drawPath(linePath, paint);
        //绘制辅助线箭头

        paint.setColor(Color.YELLOW);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1, -1);   //将y轴的方向调整

        RectF rectF = new RectF(-200, -200, 200, 200);
        src.addRect(rectF, Path.Direction.CCW);  //Path.Direction.CW,  绘制顺序正方向
        src.setLastPoint(-300, 300);
        dist.addCircle(0, -200, 100, Path.Direction.CW);

        src.addPath(dist);

        Path arcPath = new Path();
        arcPath.lineTo(250, 250);
        arcPath.arcTo(new RectF(0, 0, 300, 300), 0, 270, false);
        arcPath.offset(100, 100);  //进行平移，相当于translate， 但是只针对当前的path
        src.addPath(arcPath);

        canvas.drawPath(src, paint);
    }
}
