package org.fireking.basic.recyclerview.v3;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.fireking.ap.R;
import org.fireking.basic.recyclerview.v2.NewsFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedViewHolder extends BaseViewHolder {

    private MagicIndicator magicIndicator;
    private ViewPager view_pager;
    private List<String> mTitleDataList = new ArrayList<>();

    private FragmentActivity fragmentActivity;

    public FeedViewHolder(@NonNull View itemView, FragmentActivity fragmentActivity) {
        super(itemView);

        this.fragmentActivity = fragmentActivity;

        magicIndicator = itemView.findViewById(R.id.magic_indicator);
        view_pager = itemView.findViewById(R.id.view_pager);

        mTitleDataList.add("推荐");
        mTitleDataList.add("养生");
        mTitleDataList.add("娱乐");
        mTitleDataList.add("视频");
        mTitleDataList.add("抗击肺炎");
        mTitleDataList.add("互联网");
        mTitleDataList.add("科技");
        mTitleDataList.add("数码");
    }

    @Override
    public void bind() {
        initViewPager();
        initViewPagerIndicator();
    }

    private void initViewPager() {
        view_pager.setAdapter(new NewsFragmentPagerAdapter(fragmentActivity.getSupportFragmentManager(), mTitleDataList));
        view_pager.setOffscreenPageLimit(mTitleDataList.size());
    }

    private void initViewPagerIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(itemView.getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#80FFFFFF"));
                colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view_pager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, view_pager);
    }
}
