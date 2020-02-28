package org.fireking.ap.custom.recyclerview.v2;

import androidx.annotation.IntDef;

@IntDef({PerpetualCalendarType.TYPE_MONTH_CALENDAR,
        PerpetualCalendarType.TYPE_DAY_CALENDAR,
        PerpetualCalendarType.TYPE_OPERATE_BANNER,
        PerpetualCalendarType.TYPE_AD_BANNER,
        PerpetualCalendarType.TYPE_NEWS_VIEW_PAGER})
public @interface PerpetualCalendarType {

    //日历-月日历
    int TYPE_MONTH_CALENDAR = 1;

    //当前月历选中日历
    int TYPE_DAY_CALENDAR = 2;

    //运营位Banner
    int TYPE_OPERATE_BANNER = 3;

    //广告位
    int TYPE_AD_BANNER = 4;

    //新闻列表
    int TYPE_NEWS_VIEW_PAGER = 5;
}
