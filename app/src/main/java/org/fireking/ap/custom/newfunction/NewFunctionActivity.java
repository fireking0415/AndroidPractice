package org.fireking.ap.custom.newfunction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.fireking.ap.databinding.ActivityNewFunctionBinding;

public class NewFunctionActivity extends AppCompatActivity {

    private ActivityNewFunctionBinding newFunctionBinding;

    public static void start(Context context) {
        context.startActivity(new Intent(context, NewFunctionActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newFunctionBinding = ActivityNewFunctionBinding.inflate(LayoutInflater.from(this));
        setContentView(newFunctionBinding.getRoot());

        newFunctionBinding.btnViewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewBindingSampleActivity.start(NewFunctionActivity.this);
            }
        });

        newFunctionBinding.btnDeeplinkOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("practice://fireking.org/testdeeplink?isDeep=true"));
                startActivity(intent);
            }
        });
    }
}
