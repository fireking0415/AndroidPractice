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
        if(uri != null){

        }
        moveTaskToBack(true);
        Log.e("info", "测试DeepLink调起页面");
    }
}
