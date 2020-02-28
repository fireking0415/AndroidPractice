package org.fireking.ap.custom.recyclerview.v2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NewsFragment extends Fragment {

    private RecyclerView inner_recyclerView;
    private NewsAdapter mNewsAdapter;

    private String mTitle;

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

        mNewsAdapter = new NewsAdapter(mTitle);

        inner_recyclerView = view.findViewById(R.id.inner_recyclerView);
        inner_recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        inner_recyclerView.setAdapter(mNewsAdapter);
    }
}
