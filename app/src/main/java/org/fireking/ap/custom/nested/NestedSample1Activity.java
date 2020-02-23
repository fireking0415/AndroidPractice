package org.fireking.ap.custom.nested;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.fireking.ap.R;

public class NestedSample1Activity extends AppCompatActivity {

    public static void start(Context context){
        context.startActivity(new Intent(context, NestedSample1Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_sample1);
    }
}
