package org.fireking.basic.recyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.haibin.calendarview.CalendarView;

import org.fireking.ap.R;
import org.fireking.basic.recyclerview.v2.BannerAdapter;
import org.fireking.basic.recyclerview.v2.BigImageAdAdapter;
import org.fireking.basic.recyclerview.v2.DayCalendarAdapter;
import org.fireking.basic.recyclerview.v2.MonthCalendarAdapter;
import org.fireking.basic.recyclerview.v2.NewsFragment;
import org.fireking.basic.recyclerview.v2.NewsPagerAdapter;
import org.greenrobot.eventbus.EventBus;

public class WanNianLiRecyclerView2Activity extends AppCompatActivity {

    private RecyclerView rv_content_list;
    private DelegateAdapter delegateAdapter;
    private VirtualLayoutManager layoutManager;

    private MonthCalendarAdapter monthCalendarAdapter;
    private DayCalendarAdapter dayCalendarAdapter;
    private BannerAdapter bannerAdapter;
    private BigImageAdAdapter bigImageAdAdapter;
    private NewsPagerAdapter newsPagerAdapter;

    private boolean mDisallowIntercept;
    private RelativeLayout calendar_title_bar;
    private RelativeLayout news_title_bar;
    private Button btnBackToCalendar;
    private TextView tvMonth;

    public static void start(Context context) {
        context.startActivity(new Intent(context, WanNianLiRecyclerView2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_nian_li_recycler_view2);

        initView();

        bindRecyclerLayoutManager();

        initRecyclerViewPool();

        initRecyclerViewAdapter();

        bindItemTouchListener();

        initListener();
    }

    private void initListener() {
        btnBackToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutManager != null) {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                    EventBus.getDefault().post(new NewsFragment.ResetRecyclerViewPositionEvent());
                }
            }
        });
    }

    private void bindRecyclerLayoutManager() {
        layoutManager = new VirtualLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically() && !mDisallowIntercept;
            }
        };
        rv_content_list.setLayoutManager(layoutManager);
    }

    private void initView() {
        calendar_title_bar = findViewById(R.id.calendar_title_bar);
        news_title_bar = findViewById(R.id.news_title_bar);
        rv_content_list = findViewById(R.id.rv_content_list);
        btnBackToCalendar = findViewById(R.id.btnBackToCalendar);
        tvMonth = findViewById(R.id.tvMonth);
    }

    private void initRecyclerViewPool() {
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        rv_content_list.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0, 10);
    }

    private void initRecyclerViewAdapter() {
        delegateAdapter = new DelegateAdapter(layoutManager);
        rv_content_list.setAdapter(delegateAdapter);

        //月份日历
        monthCalendarAdapter = new MonthCalendarAdapter(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tvMonth.setText(year + "年" + month + "月");
            }
        });
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
    }

    private void bindItemTouchListener() {
        rv_content_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.e("info", "newState---->" + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.e("info", "onScrolled---->" + dx + ", " + dy + ", " + recyclerView.getScrollState());
                VirtualLayoutManager virtualLayoutManager = (VirtualLayoutManager) recyclerView.getLayoutManager();
                if (virtualLayoutManager != null) {
                    int firstItemPosition = virtualLayoutManager.findFirstVisibleItemPosition();
                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(firstItemPosition);
                    if (viewHolder != null) {

                        if (viewHolder instanceof NewsPagerAdapter.NewsPagerViewHolder) {
                            showCalendarBar(false);
                        } else {
                            showCalendarBar(true);
                        }
                    }
                }
            }
        });

        rv_content_list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent event) {
                VirtualLayoutManager virtualLayoutManager = (VirtualLayoutManager) rv.getLayoutManager();
                if (virtualLayoutManager != null) {
                    boolean isBigImageAdShow = false;
                    for (int i = 0; i < virtualLayoutManager.getChildCount(); i++) {
                        View itemView = virtualLayoutManager.getChildAt(i);
                        if (itemView != null) {
                            RecyclerView.ViewHolder viewHolder = rv.getChildViewHolder(itemView);
                            if (viewHolder instanceof BigImageAdAdapter.BigImageAdViewHolder) {
                                BigImageAdAdapter.BigImageAdViewHolder bigImageAdViewHolder = (BigImageAdAdapter.BigImageAdViewHolder) viewHolder;
                                isBigImageAdShow = bigImageAdViewHolder.isBigImageAdShow();
                            }
                        }
                    }
                    int firstItemPosition = virtualLayoutManager.findFirstVisibleItemPosition();
                    RecyclerView.ViewHolder viewHolder = rv.findViewHolderForAdapterPosition(firstItemPosition);
                    if (viewHolder != null) {
                        if (viewHolder instanceof NewsPagerAdapter.NewsPagerViewHolder) {
                            ((ViewGroup) viewHolder.itemView).requestDisallowInterceptTouchEvent(true);
                            mDisallowIntercept = true;
                        } else {
                            ((ViewGroup) viewHolder.itemView).requestDisallowInterceptTouchEvent(false);
                            mDisallowIntercept = false;
                        }

                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (isBigImageAdShow) {
                                if (viewHolder instanceof BigImageAdAdapter.BigImageAdViewHolder) {
                                    scrollToNewsViewPager(viewHolder.itemView);
                                }
                            } else {
                                if (viewHolder instanceof BannerAdapter.BannerViewHolder) {
                                    scrollToNewsViewPager(viewHolder.itemView);
                                }
                            }
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
            }
        });
    }

    private void scrollToNewsViewPager(View itemView) {
        Rect bannerRect = new Rect();
        itemView.getGlobalVisibleRect(bannerRect);
        if (bannerRect.bottom - bannerRect.top < itemView.getHeight() * 3 / 2) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(bannerRect.bottom - bannerRect.top);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int scrollY = (int) animation.getAnimatedValue();
                    rv_content_list.scrollBy(0, scrollY);
                }
            });
            valueAnimator.setDuration(800);
            valueAnimator.start();
        }
    }

    private void showCalendarBar(boolean isShowCalendar) {
        calendar_title_bar.setVisibility(isShowCalendar ? View.VISIBLE : View.GONE);
        news_title_bar.setVisibility(isShowCalendar ? View.GONE : View.VISIBLE);
    }
}
