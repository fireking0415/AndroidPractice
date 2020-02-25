package org.fireking.ap.custom.recyclerview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class WanNianliHomeFragmentAdapter extends FragmentStatePagerAdapter {

    private List<String> titleLists;

    public WanNianliHomeFragmentAdapter(@NonNull FragmentManager fm, List<String> titles) {
        super(fm);
        this.titleLists = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WanNianLiNewsFragment.createFragment(titleLists.get(position));
    }

    @Override
    public int getCount() {
        return titleLists.size();
    }
}
