package org.fireking.ap.custom.image.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;

import org.fireking.ap.R;

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
public class MatrixDescDialog extends CenterPopupView {

    public MatrixDescDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.matrix_desc_layout;
    }
}
