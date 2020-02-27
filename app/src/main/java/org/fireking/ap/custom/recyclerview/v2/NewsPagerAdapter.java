package org.fireking.ap.custom.recyclerview.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import org.fireking.ap.R;

public class NewsPagerAdapter extends DelegateAdapter.Adapter<NewsPagerAdapter.NewsPagerViewHolder> {

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public NewsPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_pager_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPagerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class NewsPagerViewHolder extends RecyclerView.ViewHolder {

        public NewsPagerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
