package org.fireking.basic.recyclerview.v2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.fireking.ap.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NewsFragment extends Fragment {

    private RecyclerView inner_recyclerView;
    private NewsAdapter mNewsAdapter;

    private String mTitle;
    private TextView tvRefreshCount;
    private SmartRefreshLayout smartRefreshLayout;

    public static class ResetRecyclerViewPositionEvent {

    }

    public static Fragment createFragment(String title) {
        Fragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString("title");
        }
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResetRecyclerViewPositionEvent(ResetRecyclerViewPositionEvent event) {
        if (event != null && inner_recyclerView != null) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) inner_recyclerView.getLayoutManager();
            if (layoutManager != null) {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvRefreshCount = view.findViewById(R.id.tvRefreshCount);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });

        mNewsAdapter = new NewsAdapter(mTitle);

        inner_recyclerView = view.findViewById(R.id.inner_recyclerView);
        inner_recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        inner_recyclerView.setAdapter(mNewsAdapter);
    }
}
