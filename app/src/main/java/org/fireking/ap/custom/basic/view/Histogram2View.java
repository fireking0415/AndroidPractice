package org.fireking.ap.custom.basic.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

public class Histogram2View extends View {

    private int width, height;
    private static final float MAX_PROGRESS = 1000F;
    private static final float MAX_RADIUS = 270F;
    private int[] progresses = {900, 880, 787, 420, 280};
    private String[] progressLabels = {"射手", "法师", "战士", "辅助", "打野"};
    private int margin = 50;
    private int maxRadius;
    private static final int fontMargin = 8;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint paintLabel = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public Histogram2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.parseColor("#F14400"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);

        paintLabel.setStrokeWidth(3);
        paintLabel.setTextSize(24);
        paintLabel.setColor(Color.parseColor("#1d5464"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY), heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;

        maxRadius = this.width * 4 / 6 / 2;
        Log.e("info", "最大半径->" + maxRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#f3f6c8"));
        canvas.translate(width / 2, height / 2);

        //计算每个进度条展示的宽度位置

        //绘制进度
        for (int i = 0; i < progresses.length; i++) {
            float currentProgess = progresses[i] / MAX_PROGRESS * MAX_RADIUS;
            RectF f = new RectF(-maxRadius + i * margin, -maxRadius + i * margin, maxRadius - i * margin, maxRadius - i * margin);
            canvas.drawArc(f, -90, currentProgess, false, paint);
        }

        for (int i = 0; i < progressLabels.length; i++) {
            Rect fontRect = new Rect();
            paintLabel.getTextBounds(progressLabels[i], 0, progressLabels[i].length(), fontRect);
            canvas.drawText(progressLabels[i], -(fontRect.width() + fontMargin), -maxRadius + i * margin + fontRect.height() / 2, paintLabel);
        }
    }
}
