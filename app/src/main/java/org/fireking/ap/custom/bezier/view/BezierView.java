package org.fireking.ap.custom.bezier.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class BezierView extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    //8个控制点
    private PointF c0;
    private PointF c1;
    private PointF c2;
    private PointF c3;
    private PointF c4;
    private PointF c5;
    private PointF c6;
    private PointF c7;

    //4个基本位置点
    private PointF p0;
    private PointF p1;
    private PointF p2;
    private PointF p3;

    //圆的基本半径
    private int radius;
    private float distance;

    private float rRadio = 1;  //P2,3,4 x轴倍数
    private float lRadio = 1;  //P8,9,10倍数
    private float tbRadio = 1;  //y轴缩放倍数

    private float disL = 0.5f;   //离开圆的阈值
    private float disM = 0.7f;  //最大值的阈值
    private float disA = 0.9f;  //到达下个圆框的阈值
    private float boundRadio = 0.55f;  //进入另一个圆的回弹效果
    private float interpolatedValue;

    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(4F);
        linePaint.setColor(Color.parseColor("#364e68"));

        circlePaint.setColor(Color.parseColor("#ef5a5a"));
        circlePaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = w / 12 * 4 / 2;
        distance = C * 0.45F * radius;

        //根据半径绘制圆的位置点
        p0 = new PointF(radius, 0);
        p1 = new PointF(0, -radius);
        p2 = new PointF(-radius, 0);
        p3 = new PointF(0, radius);

        //根据半径绘制圆的控制点
        c0 = new PointF(radius, -radius * C);
        c1 = new PointF(radius * C, -radius);
        c2 = new PointF(-radius * C, -radius);
        c3 = new PointF(-radius, -radius * C);
        c4 = new PointF(-radius, radius * C);
        c5 = new PointF(-radius * C, radius);
        c6 = new PointF(radius * C, radius);
        c7 = new PointF(radius, radius * C);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1, -1);

        //绘制数学坐标系
        canvas.drawLine(-getWidth(), 0, getWidth(), 0, linePaint);
        canvas.drawLine(0, getHeight(), 0, -getHeight(), linePaint);

        canvas.drawCircle(0, 0, radius, linePaint);

        //开始绘制
        Path circlePath = new Path();
        circlePath.moveTo(p0.x * rRadio, p0.y);
        circlePath.cubicTo(c0.x * rRadio, c0.y, c1.x, c1.y * tbRadio, p1.x, p1.y * tbRadio);
        circlePath.cubicTo(c2.x, c2.y * tbRadio, c3.x * lRadio, c3.y, p2.x * lRadio, p2.y);
        circlePath.cubicTo(c4.x * lRadio, c4.y, c5.x, c5.y * tbRadio, p3.x, p3.y * tbRadio);
        circlePath.cubicTo(c6.x, c6.y * tbRadio, c7.x * rRadio, c7.y, p0.x * rRadio, p0.y);
        canvas.drawPath(circlePath, circlePaint);
    }

    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                interpolatedValue = (float) animation.getAnimatedValue();
                if (interpolatedValue > 0 && interpolatedValue <= disL) {  //拉伸开始，圆高度未发生形变
                    rRadio = 1f + interpolatedValue * 2;              //[1,2]
                    tbRadio = 1f;
                } else if (interpolatedValue > disL && interpolatedValue <= disM) {  //圆高度发生形变，变成椭圆
                    rRadio = 2 - range0Until1(disL, disM) * 0.5f;          //  [2,1.5]
                    lRadio = 1 + range0Until1(disL, disM) * 0.5f;          // [1,1.5]
                    tbRadio = 1 - range0Until1(disL, disM) / 3;           // [1 , 2/3]
                } else if (interpolatedValue > 0.8 && interpolatedValue <= disA) { //圆减速挤压过程
                    rRadio = 1.5f - range0Until1(disM, disA) * 0.5f;     //  [1.5,1]
                    lRadio = 1.5f - range0Until1(disM, disA) * 0.5f;     //  [1.5,1]
                    tbRadio = (range0Until1(disM, disA) + 2) / 3;        // [ 2/3,1]
                } else if (disA < interpolatedValue && interpolatedValue <= 1f) {//到达圆框，lRadio=[boundRadio,1]
                    rRadio = 1;
                    lRadio = 1;
                    lRadio = boundRadio + range0Until1(disA, 1) * (1 - boundRadio);     //反弹效果，饱和
                }

                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    private float range0Until1(float minValue, float maxValue) {
        return (interpolatedValue - minValue) / (maxValue - minValue);
    }
}
