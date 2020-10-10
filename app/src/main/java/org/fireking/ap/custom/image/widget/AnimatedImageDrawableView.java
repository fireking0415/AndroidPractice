package org.fireking.ap.custom.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import org.fireking.ap.R;

import java.io.IOException;

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/10/10
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 *
 * @author Wanggang
 */
public class AnimatedImageDrawableView extends View {

    private Drawable aniDrawable;
    private float scale;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public AnimatedImageDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        try {
            aniDrawable =
                    ImageDecoder.decodeDrawable(ImageDecoder.createSource(getResources(), R.mipmap.ic_banner));
            scale = getResources().getDisplayMetrics().widthPixels * 1F / aniDrawable.getIntrinsicWidth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        aniDrawable.draw(canvas);
        canvas.restore();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (aniDrawable instanceof AnimatedImageDrawable) {
            ((AnimatedImageDrawable) aniDrawable).start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (aniDrawable instanceof AnimatedImageDrawable) {
            ((AnimatedImageDrawable) aniDrawable).stop();
        }
    }
}
