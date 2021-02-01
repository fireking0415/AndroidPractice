package org.fireking.basic.nested;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.ViewCompat;

import org.fireking.ap.R;

public class ElemeDetailLayout extends LinearLayout implements NestedScrollingParent2 {

    private View edv_content;
    private View edv_header;
    private View edv_title;

    private int titleHeight;
    private int headerHeight;

    private OnChangedListener onChangedListener;

    public ElemeDetailLayout(Context context) {
        super(context);
    }

    public ElemeDetailLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElemeDetailLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ElemeDetailLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnChangedListener(OnChangedListener onChangedListener){
        this.onChangedListener = onChangedListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        edv_content = findViewById(R.id.edv_content);
        edv_header = findViewById(R.id.edv_header);
        edv_title = findViewById(R.id.edv_title);

        edv_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){

            @Override
            public boolean onPreDraw() {
                if(onChangedListener != null){
                    float fraction = (edv_content.getY() - titleHeight) / headerHeight;
                    onChangedListener.onChanged(fraction);
                }
                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        titleHeight = edv_title.getMeasuredHeight();
        headerHeight = edv_header.getMeasuredHeight();

        int realHeightMeasureSpec = MeasureSpec.makeMeasureSpec(headerHeight + getMeasuredHeight(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, realHeightMeasureSpec);
    }

    private void offset(int dy, int[] consumed){
        ViewCompat.offsetTopAndBottom(edv_content, -dy);
        consumed[0] =0;
        consumed[1] = dy;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        float supposeY = edv_content.getY() - dy;
        if(dy > 0){
            if(supposeY >= titleHeight){
                offset(dy, consumed);
            }else{
                offset((int)(edv_content.getY() - titleHeight), consumed);
            }
        }

        //向下移动
        if(dy < 0){
            //表示target不能向下移动
            if(!ViewCompat.canScrollVertically(target, dy)){
                if(supposeY<= titleHeight + headerHeight){
                    offset(dy, consumed);
                }else{
                    offset((int)(edv_content.getY()- headerHeight - titleHeight), consumed);
                }
            }
        }
    }

    public interface OnChangedListener{

        void onChanged(float fraction);
    }
}
