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

public class DayCalendarAdapter extends DelegateAdapter.Adapter<DayCalendarAdapter.DayCalendarViewHolder> {

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public DayCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayCalendarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.day_calendar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DayCalendarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class DayCalendarViewHolder extends RecyclerView.ViewHolder {

        public DayCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
