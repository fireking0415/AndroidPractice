package org.fireking.ap.custom.recyclerview.v3;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import org.fireking.ap.R;

public class AdViewHolder extends BaseViewHolder {

    private Button btnCloseAd;
    private RelativeLayout rl_ad_container;
    private NotifyChangedListener notifyChangedListener;

    public AdViewHolder(@NonNull View itemView, NotifyChangedListener notifyChangedListener) {
        super(itemView);
        this.notifyChangedListener = notifyChangedListener;
        btnCloseAd = itemView.findViewById(R.id.btnCloseAd);
        rl_ad_container = itemView.findViewById(R.id.rl_ad_container);
    }

    @Override
    public void bind() {

        btnCloseAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_ad_container.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                if (notifyChangedListener != null) {
                    notifyChangedListener.onNotifyChanged();
                }
            }
        });
    }

    public interface NotifyChangedListener {
        void onNotifyChanged();
    }

    public boolean isBigImageAdShow() {
        return rl_ad_container.getLayoutParams().height != 0;
    }
}
