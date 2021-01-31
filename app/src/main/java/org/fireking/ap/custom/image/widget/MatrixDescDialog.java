package org.fireking.ap.custom.image.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;

import org.fireking.ap.R;

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
