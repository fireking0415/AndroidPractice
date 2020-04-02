package org.fireking.ap.custom.recyclerview.v3;

import android.view.View;

import androidx.annotation.NonNull;

import com.haibin.calendarview.CalendarView;

import org.fireking.ap.R;

public class CalendarViewHolder extends BaseViewHolder {

    private OnCalendarChangedListener mOnCalendarChangedListener;

    private CalendarView calendarView;

    public CalendarViewHolder(@NonNull View itemView, OnCalendarChangedListener onCalendarChangedListener) {
        super(itemView);
        calendarView = itemView.findViewById(R.id.calendarView);
        this.mOnCalendarChangedListener = onCalendarChangedListener;
    }

    @Override
    public void bind() {
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                if (mOnCalendarChangedListener != null) {
                    mOnCalendarChangedListener.onCalendarChanged(year, month);
                }
            }
        });
    }
}
