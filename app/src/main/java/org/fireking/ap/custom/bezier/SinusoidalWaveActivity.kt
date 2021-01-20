package org.fireking.ap.custom.bezier

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivitySinusoidalWaveBinding
import org.jetbrains.anko.intentFor

class SinusoidalWaveActivity : AppCompatActivity() {

    private var viewBinding: ActivitySinusoidalWaveBinding? = null

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<SinusoidalWaveActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySinusoidalWaveBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.seekbarW?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewBinding?.sinusoidalWaveView?.setAngularFrequency(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })


        viewBinding?.seekBarR?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewBinding?.sinusoidalWaveView?.setPhaseAngle(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        viewBinding?.seekBarA?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewBinding?.sinusoidalWaveView?.setAmplitude(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val animator = ValueAnimator.ofInt(0, metrics.widthPixels)
        animator.duration = 10000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { animation ->
            viewBinding?.sinusoidalWaveView?.setPhaseAngle(
                animation.animatedValue as Int
            )
        }
        animator.start()
    }
}
