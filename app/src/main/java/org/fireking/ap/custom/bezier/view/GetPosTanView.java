package org.fireking.ap.custom.bezier.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import org.fireking.ap.R;

public class GetPosTanView extends View {

    private float currentValue;
    private float[] pos;
    private float[] tan;
    private Bitmap bitmap;
    private Matrix mMatrix;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GetPosTanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.path_arrow, options);
        mMatrix = new Matrix();

        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path, false);

        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        pathMeasure.getPosTan(pathMeasure.getLength() * currentValue, pos, tan);
//
//        Log.e("info", "pos : " + pos[0] + ", " + pos[1] + "  tan: " + tan[0] + ", " + tan[1]);
//
//        mMatrix.reset();
//        float degrees = (float) (Math.atan2(tan[1], tan[0] * 180.0 / Math.PI));
//        mMatrix.postRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        mMatrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);

        pathMeasure.getMatrix(pathMeasure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);

        canvas.drawPath(path, paint);
        canvas.drawBitmap(bitmap, mMatrix, paint);

        invalidate();
    }
}
