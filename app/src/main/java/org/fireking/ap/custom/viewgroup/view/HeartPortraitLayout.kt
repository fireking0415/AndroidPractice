package org.fireking.ap.custom.viewgroup.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import org.fireking.ap.R

class HeartPortraitLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var ivHeaderPortrait: ZoomInAnimatorImageView
    private var waveBackgroundView: WaveBackgroundView

    init {
        LayoutInflater.from(context).inflate(R.layout.heart_portrait_layout, this, true)
        ivHeaderPortrait = findViewById(R.id.ivHeaderPortrait)
        ivHeaderPortrait.isRoundAsCircle = true
        waveBackgroundView = findViewById(R.id.waveBackgroundView)
    }
}