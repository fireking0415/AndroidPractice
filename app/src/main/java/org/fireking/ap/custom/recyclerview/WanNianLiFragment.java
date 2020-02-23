package org.fireking.ap.custom.recyclerview;

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
import org.fireking.ap.custom.recyclerview.adapter.WanNianLiItemAdapter;

public class WanNianLiFragment extends Fragment {

    public static Fragment createFragment() {
        return new WanNianLiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.news_fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView rv_news_item = getView().findViewById(R.id.rv_news_item);
        rv_news_item.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv_news_item.setAdapter(new WanNianLiItemAdapter());
    }
}
