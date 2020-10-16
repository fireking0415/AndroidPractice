package org.fireking.ap;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/10/16
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 *
 * @author Wanggang
 */
public class AndroidPracticeApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(base);
    }
}
