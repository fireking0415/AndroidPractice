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
    public void onBindViewHolder(@NonNull BigImageAdViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class BigImageAdViewHolder extends RecyclerView.ViewHolder {

        public BigImageAdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
