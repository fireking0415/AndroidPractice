package org.fireking.basic.view.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class PieChat2View extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int width;
    private int height;
    private int radius;
    private static final int padding = 20;

    private ComparingRule[] comparingRules;

    public PieChat2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        comparingRules = new ComparingRule[]{
                new ComparingRule(Color.parseColor("#1d5464"), "射手", 90),
                new ComparingRule(Color.parseColor("#c1224f"), "坦克", 75),
                new ComparingRule(Color.parseColor("#0b032d"), "辅助", 162),
                new ComparingRule(Color.parseColor("#6b76ff"), "打野", 48),
                new ComparingRule(Color.parseColor("#45b7b7"), "法师", 250),
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        radius = h / 4 * 3 / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制比例尺
        canvas.save();
        canvas.translate(0, height);  //移动坐标系 方便绘制
        float ruleWidth = (width - 2 * padding) / 4 * 3;
        float startWidth = width - padding - ruleWidth;
        float ruleItemWidth = ruleWidth / comparingRules.length;
        for (int i = 0; i < comparingRules.length; i++) {
            paint.setColor(Color.parseColor("#071e3d"));
            paint.setStrokeWidth(2);
            paint.setTextSize(24);
            paint.setStyle(Paint.Style.STROKE);
            Rect fontRect = new Rect();
            paint.getTextBounds(comparingRules[i].label, 0, comparingRules[i].label.length(), fontRect);
            canvas.drawText(comparingRules[i].label, startWidth + i * ruleItemWidth + fontRect.width() / 2, -fontRect.height() / 2 - padding, paint);
            paint.setColor(comparingRules[i].color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(new RectF(startWidth + i * ruleItemWidth - 10, -fontRect.height() - padding - 10,
                    startWidth + i * ruleItemWidth + 10, -fontRect.height() - padding + 10), paint);
            paint.reset();
        }
        canvas.restore();

        //绘制饼状图
        float countSize = 0;
        for (int i = 0; i < comparingRules.length; i++) {
            countSize += comparingRules[i].radius;
        }
        //每一度的所占数据
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        float singleSize = countSize / 360F;
        float startRadius = 0;

        int startR = 0;
        for (int i = 1; i <= 3; i++) {
            piePaint.setColor(Color.argb(255, (int) (Math.random() * 255F), (int) (Math.random() * 255F), (int) (Math.random() * 255F)));
            canvas.drawArc(new RectF(-radius + 40, -radius + 40, radius - 40, radius - 40), startR, 120, true, piePaint);
            startR += 120;
        }

        for (int i = 0; i < comparingRules.length; i++) {
            piePaint.setColor(comparingRules[i].color);
            piePaint.setStyle(Paint.Style.STROKE);
            piePaint.setStrokeWidth(80);
            canvas.drawArc(new RectF(-radius + 40, -radius + 40, radius - 40, radius - 40), startRadius, comparingRules[i].radius / singleSize + 0.5F, false, piePaint);

            piePaint.setStyle(Paint.Style.STROKE);
            piePaint.setColor(Color.BLACK);
            piePaint.setStrokeWidth(3);

            //绘制占比比例,先计算每个块的弧度中心点坐标，然后划线绘制文字
            float pStartX = (float) (Math.cos(Math.toRadians(startRadius + comparingRules[i].radius / singleSize / 2)) * radius);
            float pStartY = (float) (Math.sin(Math.toRadians(startRadius + comparingRules[i].radius / singleSize / 2)) * radius);

            float pEndX = (float) (Math.cos(Math.toRadians(startRadius + comparingRules[i].radius / singleSize / 2)) * (radius + 40));
            float pEndY = (float) (Math.sin(Math.toRadians(startRadius + comparingRules[i].radius / singleSize / 2)) * (radius + 40));
            canvas.drawLine(pStartX, pStartY, pEndX, pEndY, piePaint);

            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(24);
            paint.setColor(Color.BLACK);

            BigDecimal decimal = new BigDecimal(comparingRules[i].radius / countSize * 100);
            String text = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%";
            Rect textRect = new Rect();
            paint.getTextBounds(text, 0, text.length(), textRect);

            if (pEndX < 0) {
                canvas.drawLine(pEndX, pEndY, pEndX - 60, pEndY, piePaint);
                canvas.drawText(text, pEndX - 60 - textRect.width(), pEndY, paint);
            } else {
                canvas.drawLine(pEndX, pEndY, pEndX + 60, pEndY, piePaint);
                canvas.drawText(text, pEndX + 60, pEndY, paint);
            }

            startRadius += comparingRules[i].radius / singleSize;
        }
        canvas.restore();
    }

    private static class ComparingRule {

        private int color;
        private String label;
        private float radius;

        private ComparingRule(int color, String label, float radius) {
            this.color = color;
            this.label = label;
            this.radius = radius;
        }

        public int getColor() {
            return color;
        }

        public String getLabel() {
            return label;
        }

        public float getRadius() {
            return radius;
        }
    }
}
