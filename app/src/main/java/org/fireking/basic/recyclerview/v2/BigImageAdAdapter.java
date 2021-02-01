package org.fireking.basic.recyclerview.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import org.fireking.ap.R;

public class BigImageAdAdapter extends DelegateAdapter.Adapter<BigImageAdAdapter.BigImageAdViewHolder> {

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public BigImageAdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BigImageAdViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.big_image_ad_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BigImageAdViewHolder holder, int position) {
        holder.btnCloseAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rl_ad_container.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class BigImageAdViewHolder extends RecyclerView.ViewHolder {

        private Button btnCloseAd;
        private RelativeLayout rl_ad_container;

        public BigImageAdViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCloseAd = itemView.findViewById(R.id.btnCloseAd);
            rl_ad_container = itemView.findViewById(R.id.rl_ad_container);
        }

        public boolean isBigImageAdShow() {
            return rl_ad_container.getLayoutParams().height != 0;
        }
    }
}
