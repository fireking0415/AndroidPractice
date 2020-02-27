package org.fireking.ap.custom.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import org.fireking.ap.R;
import org.fireking.ap.custom.recyclerview.v2.BannerAdapter;
import org.fireking.ap.custom.recyclerview.v2.BigImageAdAdapter;
import org.fireking.ap.custom.recyclerview.v2.DayCalendarAdapter;
import org.fireking.ap.custom.recyclerview.v2.MonthCalendarAdapter;
import org.fireking.ap.custom.recyclerview.v2.NewsPagerAdapter;

public class WanNianLiRecyclerView2Activity extends AppCompatActivity {

    private RecyclerView rv_content_list;
    private DelegateAdapter delegateAdapter;

    private MonthCalendarAdapter monthCalendarAdapter;
    private DayCalendarAdapter dayCalendarAdapter;
    private BannerAdapter bannerAdapter;
    private BigImageAdAdapter bigImageAdAdapter;
    private NewsPagerAdapter newsPagerAdapter;

    private boolean mDisallowIntercept;


    public static void start(Context context) {
        context.startActivity(new Intent(context, WanNianLiRecyclerView2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_nian_li_recycler_view2);

        rv_content_list = findViewById(R.id.rv_content_list);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        rv_content_list.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0, 10);

        delegateAdapter = new DelegateAdapter(layoutManager);

        rv_content_list.setLayoutManager(new LinearLayoutManager(this));
        rv_content_list.setAdapter(delegateAdapter);

        //月份日历
        monthCalendarAdapter = new MonthCalendarAdapter();
        delegateAdapter.addAdapter(monthCalendarAdapter);

        //选中日期-当日运势
        dayCalendarAdapter = new DayCalendarAdapter();
        delegateAdapter.addAdapter(dayCalendarAdapter);

        //Banner运营位
        bannerAdapter = new BannerAdapter();
        delegateAdapter.addAdapter(bannerAdapter);

        //大图广告
        bigImageAdAdapter = new BigImageAdAdapter();
        delegateAdapter.addAdapter(bigImageAdAdapter);

        //新闻列表
        newsPagerAdapter = new NewsPagerAdapter(this);
        delegateAdapter.addAdapter(newsPagerAdapter);

        rv_content_list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (mDisallowIntercept) {
                    mDisallowIntercept = false;
                    return true;
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
                if (linearLayoutManager != null) {
                    int firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    RecyclerView.ViewHolder viewHolder = rv.findViewHolderForAdapterPosition(firstItemPosition);
                    if (viewHolder != null) {
                        if (viewHolder instanceof NewsPagerAdapter.NewsPagerViewHolder) {
                            ((ViewGroup) viewHolder.itemView).requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                mDisallowIntercept = disallowIntercept;
            }
        });
    }
}
