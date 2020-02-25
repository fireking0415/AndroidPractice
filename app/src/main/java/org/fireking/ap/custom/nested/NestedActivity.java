package org.fireking.ap.custom.nested;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.fireking.ap.R;

public class NestedActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, NestedActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);

        findViewById(R.id.btnNested1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NestedSample1Activity.start(NestedActivity.this);
            }
        });

        findViewById(R.id.btnNested2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NestedSample2Activity.start(NestedActivity.this);
            }
        });

        findViewById(R.id.btnNested3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NestedSample3Activity.start(NestedActivity.this);
            }
        });

        findViewById(R.id.btnNested4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NestedSample4Activity.start(NestedActivity.this);
            }
        });
    }
}
