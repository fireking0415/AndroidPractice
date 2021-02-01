package org.fireking.basic.recyclerview.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private String title;

    private List<String> temps = new ArrayList<>();

    public NewsAdapter(String mTitle) {
        this.title = mTitle;
        for (int i = 0; i < 20; i++) {
            temps.add("test");
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(temps.get(position));
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public void addItem() {
        temps.add(10, "");
        notifyItemRangeInserted(10, 1);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvLabel;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tvLabel);
        }

        public void bind(String title) {
            tvLabel.setText(title + "-" + getAdapterPosition());
        }
    }
}
