package com.backbase.mobileAssignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.backbase.mobileAssignment.databinding.FragmentHomeBinding
import com.backbase.mobileAssignment.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}