package org.fireking.testdeeplinkapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = getIntent().getData();
        if (uri != null && "true".equals(uri.getQueryParameter("isDeep"))) {
            Log.e("info", "使用Deeplink调起App页面");
            moveTaskToBack(true);
        } else {
            Log.e("info", "正常方式打开页面");
        }

    }
}
