package org.fireking.basic.recyclerview.v2;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.fireking.ap.R;

import java.util.ArrayList;
import java.util.List;

public class News2Adapter extends RecyclerView.Adapter<News2Adapter.NewsViewHolder> {

    private List<TempBean> temps = new ArrayList<>();

    public News2Adapter() {
        for (int i = 0; i < 20; i++) {
            temps.add(new TempBean("每日一签", "http://geek-calendar-test.oss-cn-shanghai.aliyuncs.com/2020-08-13/1597321335253wCJdtc.png"));
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_fragment_item2, parent, false));
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
        temps.add(10, new TempBean("每日一卦", "http://geek-calendar-test.oss-cn-shanghai.aliyuncs.com/2020-08-14/1597387889946C2XQqb.png"));
//        notifyItemRangeInserted(10, 1);
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvLabel;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tvLabel);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(TempBean tempBean) {
            tvLabel.setText(tempBean.title + "-" + getAdapterPosition());
            Glide.with(imageView.getContext()).asDrawable().load(tempBean.url).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    imageView.setImageDrawable(resource);
                }
            });
        }
    }
}
