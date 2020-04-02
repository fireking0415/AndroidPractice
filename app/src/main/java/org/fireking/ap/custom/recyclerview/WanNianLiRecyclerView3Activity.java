package org.fireking.ap.custom.recyclerview;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;

import org.fireking.ap.R;
import org.fireking.ap.custom.recyclerview.v2.NewsFragment;
import org.fireking.ap.custom.recyclerview.v3.AdViewHolder;
import org.fireking.ap.custom.recyclerview.v3.FeedViewHolder;
import org.fireking.ap.custom.recyclerview.v3.OnCalendarChangedListener;
import org.fireking.ap.custom.recyclerview.v3.OperationViewHolder;
import org.fireking.ap.custom.recyclerview.v3.WanNianLiRecyclerViewV3Adapter;
import org.greenrobot.eventbus.EventBus;

public class WanNianLiRecyclerView3Activity extends AppCompatActivity {

    private RecyclerView rv_content_list;
    private LinearLayoutManager layoutManager;

    private boolean mDisallowIntercept;
    private RelativeLayout calendar_title_bar;
    private RelativeLayout news_title_bar;
    private Button btnBackToCalendar;
    private TextView tvMonth;

    private ImmersionBar immersionBar;
    private WanNianLiRecyclerViewV3Adapter recyclerViewV3Adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, WanNianLiRecyclerView3Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_nian_li_recycler_view3);

        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarDarkFont(false, 1F).statusBarColor(R.color.colorPrimary).init();

        initView();

        bindRecyclerLayoutManager();

        initRecyclerViewPool();

        initRecyclerViewAdapter();

//        bindItemTouchListener();

//        initListener();
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
        layoutManager = new LinearLayoutManager(this) {
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
        rv_content_list.setAdapter(recyclerViewV3Adapter = new WanNianLiRecyclerViewV3Adapter(this, new OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(int year, int month) {
                tvMonth.setText(year + "年" + month + "月");
            }
        }));

        recyclerViewV3Adapter.submitList();
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
                LinearLayoutManager virtualLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (virtualLayoutManager != null) {
                    int firstItemPosition = virtualLayoutManager.findFirstVisibleItemPosition();
                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(firstItemPosition);
                    if (viewHolder != null) {

                        if (viewHolder instanceof FeedViewHolder) {
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
                LinearLayoutManager virtualLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
                if (virtualLayoutManager != null) {
                    boolean isBigImageAdShow = false;
                    for (int i = 0; i < virtualLayoutManager.getChildCount(); i++) {
                        View itemView = virtualLayoutManager.getChildAt(i);
                        if (itemView != null) {
                            RecyclerView.ViewHolder viewHolder = rv.getChildViewHolder(itemView);
                            if (viewHolder instanceof AdViewHolder) {
                                AdViewHolder bigImageAdViewHolder = (AdViewHolder) viewHolder;
                                isBigImageAdShow = bigImageAdViewHolder.isBigImageAdShow();
                            }
                        }
                    }
                    int firstItemPosition = virtualLayoutManager.findFirstVisibleItemPosition();
                    RecyclerView.ViewHolder viewHolder = rv.findViewHolderForAdapterPosition(firstItemPosition);
                    if (viewHolder != null) {
                        if (viewHolder instanceof FeedViewHolder) {
                            ((ViewGroup) viewHolder.itemView).requestDisallowInterceptTouchEvent(true);
                            mDisallowIntercept = true;
                        } else {
                            ((ViewGroup) viewHolder.itemView).requestDisallowInterceptTouchEvent(false);
                            mDisallowIntercept = false;
                        }

                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (isBigImageAdShow) {
                                if (viewHolder instanceof AdViewHolder) {
                                    scrollToNewsViewPager(viewHolder.itemView);
                                }
                            } else {
                                if (viewHolder instanceof OperationViewHolder) {
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
        if (isShowCalendar) {
            immersionBar.statusBarDarkFont(false, 1F).statusBarColor(R.color.colorPrimary).init();
        } else {
            immersionBar.statusBarDarkFont(true, 1F).statusBarColor(R.color.white).init();
        }
    }
}
