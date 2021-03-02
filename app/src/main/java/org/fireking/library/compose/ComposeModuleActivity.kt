package org.fireking.library.compose

import org.fireking.ap.databinding.ActivityComposeModuleBinding
import org.fireking.base.BaseActivity

class ComposeModuleActivity : BaseActivity<ActivityComposeModuleBinding>() {

    override fun initView() {
        bindView {
            btnComposeLayout.setOnClickListener {
                ComposeLayoutActivity.start(this@ComposeModuleActivity)
            }
        }
    }
}