package org.fireking.ap.custom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.fireking.ap.R;

public class ImageTestActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, ImageTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        final ImageView iv_imageView1 = findViewById(R.id.iv_imageView1);
        findViewById(R.id.btnLoad1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ImageTestActivity.this)
                        .load("https://wxt.sinaimg.cn/mw1024/006hHB37ly1g6q0tznscjj30j60mbtc8.jpg")
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(iv_imageView1);
            }
        });

        final ImageView iv_imageView2 = findViewById(R.id.iv_imageView2);
        findViewById(R.id.btnLoad2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ImageTestActivity.this).asDrawable()
                        .load("https://wxt.sinaimg.cn/mw1024/006hHB37ly1g6q0tznscjj30j60mbtc8.jpg")
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new ImageViewTarget<Drawable>(iv_imageView2) {

                            @Override
                            protected void setResource(@Nullable Drawable resource) {
//                                BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
//                                if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null) {
//                                    iv_imageView2.setImageBitmap(bitmapDrawable.getBitmap());
//                                } else {
//                                    iv_imageView2.setImageBitmap(null);
//                                }
                                iv_imageView2.setImageDrawable(resource);
                            }
                        });
            }
        });

        final ImageView iv_imageView3 = findViewById(R.id.iv_imageView3);
        findViewById(R.id.btnLoad3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ImageTestActivity.this).asDrawable()
                        .load("https://wxt.sinaimg.cn/mw1024/006hHB37ly1g6q0tznscjj30j60mbtc8.jpg")
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new ImageViewTarget<Drawable>(iv_imageView3) {

                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                            }

                            @Override
                            protected void setResource(@Nullable Drawable resource) {
                                iv_imageView3.setImageDrawable(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                super.onLoadCleared(placeholder);
                                Glide.with(ImageTestActivity.this).clear(iv_imageView3);
                            }
                        });
            }
        });
    }
}