package org.fireking.ap.custom.recyclerview.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import org.fireking.ap.R;

public class MonthCalendarAdapter extends DelegateAdapter.Adapter<MonthCalendarAdapter.MonthCalendarViewHolder> {

    private  com.haibin.calendarview.CalendarView.OnMonthChangeListener mOnDateChangedListener;

    public MonthCalendarAdapter( com.haibin.calendarview.CalendarView.OnMonthChangeListener onDateChangeListener) {
        this.mOnDateChangedListener = onDateChangeListener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public MonthCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthCalendarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.month_calendar_item, parent, false), mOnDateChangedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthCalendarViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class MonthCalendarViewHolder extends RecyclerView.ViewHolder {

        com.haibin.calendarview.CalendarView calendarView;

        private com.haibin.calendarview.CalendarView.OnMonthChangeListener mOnDateChangedListener;

        public MonthCalendarViewHolder(@NonNull View itemView, com.haibin.calendarview.CalendarView.OnMonthChangeListener onDateChangedListener) {
            super(itemView);
            this.mOnDateChangedListener = onDateChangedListener;
            calendarView = itemView.findViewById(R.id.calendarView);
        }

        public void bind() {
            calendarView.setOnMonthChangeListener(mOnDateChangedListener);
        }
    }
}
