package org.fireking.ap.custom.recyclerview.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.fireking.ap.custom.recyclerview.WanNianLiFragment;

import java.util.List;

public class WanNianliNewsFragmentAdapter extends FragmentStatePagerAdapter {

    private List<String> itemLists;

    public WanNianliNewsFragmentAdapter(@NonNull FragmentManager fm, List<String> items) {
        super(fm);
        this.itemLists = items;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WanNianLiFragment.createFragment();
    }

    @Override
    public int getCount() {
        return itemLists.size();
    }
}
