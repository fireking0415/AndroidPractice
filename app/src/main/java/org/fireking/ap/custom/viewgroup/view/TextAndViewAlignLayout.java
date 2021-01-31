package org.fireking.ap.custom.viewgroup.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.fireking.ap.R;

public class TextAndViewAlignLayout extends RelativeLayout {

    private TextView tv_ad_desc;
    private LinearLayout ll_ad_logo;
    private ImageView iv_ad_icon;

    private int mMaxTextLength;

    private static final String SAMPLE_TEXT = "两";
    private int mBottomMargin;

    public TextAndViewAlignLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.text_and_view_align_layout, this);

        tv_ad_desc = findViewById(R.id.tv_ad_desc);
        ll_ad_logo = findViewById(R.id.ll_ad_logo);
        iv_ad_icon = findViewById(R.id.iv_ad_icon);

        mBottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int logoMeasureWidth = ll_ad_logo.getMeasuredWidth();
        int descMeasureWidth = tv_ad_desc.getMeasuredWidth();
        int descMeasureHeight = tv_ad_desc.getMeasuredHeight();
        super.onMeasure(MeasureSpec.makeMeasureSpec(descMeasureWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(descMeasureHeight, MeasureSpec.EXACTLY));
        float singletonWidth = tv_ad_desc.getPaint().measureText(SAMPLE_TEXT);
        mMaxTextLength = (int) ((descMeasureWidth - logoMeasureWidth) / singletonWidth + descMeasureWidth / singletonWidth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        ll_ad_logo.layout(ll_ad_logo.getLeft(), ll_ad_logo.getTop() - mBottomMargin, ll_ad_logo.getRight(),
                ll_ad_logo.getBottom() - mBottomMargin);
    }

    public void setAlignTextAndView(final String text, final int icon) {
        post(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null) {
                    return;
                }
                iv_ad_icon.setImageResource(icon);
                String realText = text;
                if (text.length() > mMaxTextLength) {
                    realText = text.substring(0, mMaxTextLength - 2) + "...";
                }
                tv_ad_desc.setText(realText);
            }
        });
    }
}
