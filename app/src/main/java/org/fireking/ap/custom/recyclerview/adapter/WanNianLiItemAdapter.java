package org.fireking.ap.custom.recyclerview.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

public class WanNianLiItemAdapter extends RecyclerView.Adapter<WanNianLiItemAdapter.NewsViewHolder> {

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Log.e("info", "----->" + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
