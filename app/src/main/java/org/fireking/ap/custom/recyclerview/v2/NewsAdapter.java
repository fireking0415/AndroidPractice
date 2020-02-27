package org.fireking.ap.custom.recyclerview.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private String title;

    public NewsAdapter(String mTitle) {
        this.title = mTitle;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(title);
    }

    @Override
    public int getItemCount() {
        return 40;
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
