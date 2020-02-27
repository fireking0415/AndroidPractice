package org.fireking.ap.custom.recyclerview.v2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.fireking.ap.R;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends DelegateAdapter.Adapter<NewsPagerAdapter.NewsPagerViewHolder> {

    private FragmentActivity fragmentActivity;

    public NewsPagerAdapter(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public NewsPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_pager_item, parent, false), fragmentActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPagerViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class NewsPagerViewHolder extends RecyclerView.ViewHolder {

        private MagicIndicator magicIndicator;
        private ViewPager view_pager;
        private List<String> mTitleDataList = new ArrayList<>();

        private FragmentActivity fragmentActivity;

        public NewsPagerViewHolder(@NonNull View itemView, FragmentActivity fragmentActivity) {
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

        public void bind() {
            initViewPager();
            initViewPagerIndicator();
        }

        private void initViewPager() {
            view_pager.setAdapter(new NewsFragmentPagerAdapter(fragmentActivity.getSupportFragmentManager(), mTitleDataList));
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

}
