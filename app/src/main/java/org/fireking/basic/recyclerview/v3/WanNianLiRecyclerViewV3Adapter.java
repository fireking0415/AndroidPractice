package org.fireking.basic.recyclerview.v3;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

import java.util.ArrayList;
import java.util.List;

public class WanNianLiRecyclerViewV3Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int ITEM_TYPE_CALENDAR = 1;
    public static final int ITEM_TYPE_OPERATION = 2;
    public static final int ITEM_TYPE_AD = 3;
    public static final int ITEM_TYPE_FEED = 4;
    private OnCalendarChangedListener onCalendarChangedListener;
    private FragmentActivity fragmentActivity;

    private List<MultiItem> multiItemList = new ArrayList<>();

    public WanNianLiRecyclerViewV3Adapter(FragmentActivity fragmentActivity, OnCalendarChangedListener onCalendarChangedListener) {
        this.onCalendarChangedListener = onCalendarChangedListener;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        if (viewType == ITEM_TYPE_CALENDAR) {
            baseViewHolder = new CalendarViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.calendar_viewholder_layout, parent, false), onCalendarChangedListener);
        } else if (viewType == ITEM_TYPE_OPERATION) {
            baseViewHolder = new OperationViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.operation_viewholder_layout, parent, false));
        } else if (viewType == ITEM_TYPE_AD) {
            baseViewHolder = new AdViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ad_viewholder_layout, parent, false), new AdViewHolder.NotifyChangedListener() {
                @Override
                public void onNotifyChanged() {
                    notifyDataSetChanged();
                }
            });
        } else if (viewType == ITEM_TYPE_FEED) {
            baseViewHolder = new FeedViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.feed_viewholder_layout, parent, false), fragmentActivity);
        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemViewType(int position) {
        return multiItemList.get(position).itemType;
    }

    @Override
    public int getItemCount() {
        return multiItemList.size();
    }

    public void submitList() {
        multiItemList.clear();
        multiItemList.add(new MultiItem(ITEM_TYPE_CALENDAR, null));
        multiItemList.add(new MultiItem(ITEM_TYPE_OPERATION, null));
        multiItemList.add(new MultiItem(ITEM_TYPE_AD, null));
        multiItemList.add(new MultiItem(ITEM_TYPE_FEED, null));
        notifyDataSetChanged();
    }
}
