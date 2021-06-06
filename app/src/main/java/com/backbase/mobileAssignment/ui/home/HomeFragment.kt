package com.backbase.mobileAssignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.mobileAssignment.databinding.FragmentHomeBinding
import com.backbase.mobileAssignment.ui.base.BaseActivity
import com.backbase.mobileAssignment.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var citiesAdapter: CitiesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as HomeActivity).obtainViewModel()
        }
        initViews()
        return mBinding.root
    }

    private fun initViews() {
        citiesAdapter = initRecyclerAdapter()
        insertCities()
    }

    private fun insertCities() {
        mBinding.viewModel?.apply {
            insertCities()
            insertCitiesLiveData.observe(viewLifecycleOwner, {
                if(it)
                    loadCities() // to fetch cities from DS
                else
                    (activity as? BaseActivity)?.displaySnackBar("Error Fetching cities")
            })
        }
    }

    private fun loadCities() {
        mBinding.viewModel?.apply {
            searchCities()
            fetchCitiesLiveData.observe(viewLifecycleOwner, {
                citiesAdapter.submitList(it)
            })
        }
    }

    private fun initRecyclerAdapter(): CitiesRecyclerAdapter {
        val itemClickListener = object : RecyclerItemClickListener {
            override fun onItemClicked(position: Int) {

//                val action = HomeFragmentDirections.actionHomeFragmentToChartFragment(stockResultsList[position].symbol)
//                findNavController().navigate(action)
            }
        }
        val adapter = CitiesRecyclerAdapter(itemClickListener)
        mBinding.recyclerview.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(activity)
        }
        return adapter
    }

    interface RecyclerItemClickListener {
        fun onItemClicked(position:Int)
    }

}