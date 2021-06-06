package com.backbase.mobileAssignment.ui.home

import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.backbase.mobileAssignment.R
import com.backbase.mobileAssignment.databinding.ActivityHomeBinding
import com.backbase.mobileAssignment.di.modules.home.inject
import com.backbase.mobileAssignment.ui.base.BaseActivity
import com.backbase.mobileAssignment.ui.base.BaseViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    fun obtainViewModel(): HomeViewModel {
        return viewModel
    }

    override fun getBaseViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getProgressIndicator(): ProgressBar {
        return mBinding.root.findViewById(R.id.progressBar)
    }

}