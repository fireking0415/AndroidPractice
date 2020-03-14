package org.fireking.ap.custom.windowmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import org.fireking.ap.R;
import org.fireking.ap.databinding.ActivityWindowManagerSampleBinding;

public class WindowManagerSampleActivity extends AppCompatActivity {

    private ActivityWindowManagerSampleBinding binding;

    public static void start(Context context) {
        context.startActivity(new Intent(context, WindowManagerSampleActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWindowManagerSampleBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        binding.btnAppInnerWindowManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInnerWindowManager();
            }
        });
    }

    private void showAppInnerWindowManager() {
        WindowManager wm = getWindowManager();
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.format = PixelFormat.TRANSPARENT;//设置图片格式，效果为背景透明

        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;  //窗口被虚拟按键遮挡问题

        wmParams.gravity = Gravity.TOP | Gravity.LEFT;

        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width = getResources().getDisplayMetrics().widthPixels;  //初始化窗口大小为设置按钮大小
        wmParams.height = 400;
        wm.addView(LayoutInflater.from(this).inflate(R.layout.ad_test_layout, null), wmParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
