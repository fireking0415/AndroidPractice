package org.fireking.ap.custom.newfunction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.fireking.ap.databinding.ActivityViewBindingSampleBinding;
import org.jetbrains.anko.ToastsKt;

public class ViewBindingSampleActivity extends AppCompatActivity {

    private ActivityViewBindingSampleBinding bindingSampleBinding;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ViewBindingSampleActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingSampleBinding = ActivityViewBindingSampleBinding.inflate(LayoutInflater.from(this));
        setContentView(bindingSampleBinding.getRoot());

        bindingSampleBinding.btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastsKt.toast(ViewBindingSampleActivity.this, "点解了Button");
            }
        });

        bindingSampleBinding.btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastsKt.toast(ViewBindingSampleActivity.this, "点击了文字");
            }
        });

        bindingSampleBinding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastsKt.toast(ViewBindingSampleActivity.this, "点击了图片");
            }
        });
    }
}
