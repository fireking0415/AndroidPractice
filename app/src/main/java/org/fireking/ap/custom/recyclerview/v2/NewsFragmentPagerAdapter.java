package org.fireking.ap.custom.recyclerview.v2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class NewsFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titleList;

    public NewsFragmentPagerAdapter(@NonNull FragmentManager fm, List<String> titleList) {
        super(fm);
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewsFragment.createFragment(titleList.get(position));
    }

    @Override
    public int getCount() {
        return titleList == null ? 0 : titleList.size();
    }
}
