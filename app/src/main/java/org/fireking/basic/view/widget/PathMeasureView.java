package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class PathMeasureView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        path.addRect(-400, -400, 400, 400, Path.Direction.CW);

        Path dst = new Path();
        dst.lineTo(-300, 300);

        PathMeasure measure = new PathMeasure(path, false);
        measure.getSegment(200, 600, dst, true);

        canvas.drawPath(dst, paint);

//        PathMeasure pathMeasure = new PathMeasure(path, false);
//        Log.e("info", "---->" + pathMeasure.getLength());
//
//        pathMeasure.nextContour();
//        Log.e("info", "======>" + pathMeasure.getLength());
    }
}
