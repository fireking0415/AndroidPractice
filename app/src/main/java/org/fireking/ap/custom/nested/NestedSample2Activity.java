package org.fireking.ap.custom.nested;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

public class NestedSample2Activity extends AppCompatActivity {

    public static void start(Context context){
        context.startActivity(new Intent(context, NestedSample2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_sample2);

        edv_title = findViewById(R.id.edv_title);
        elemeDetail = findViewById(R.id.elemeDetail);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MainAdapter());

        elemeDetail.setOnChangedListener(new ElemeDetailLayout.OnChangedListener() {
            @Override
            public void onChanged(float fraction) {
                edv_title.setAlpha(1-fraction);
            }
        });
    }

    private RecyclerView rv;
    private TextView edv_title;
    private ElemeDetailLayout elemeDetail;
}
