package org.fireking.ap.custom.recyclerview.v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.fireking.ap.R;

public class NewsFragment extends Fragment {

    private RecyclerView inner_recyclerView;
    private NewsAdapter mNewsAdapter;

    private String mTitle;

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
