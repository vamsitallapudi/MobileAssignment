package com.backbase.mobileAssignment.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.mobileAssignment.databinding.FragmentHomeBinding
import com.backbase.mobileAssignment.ui.base.BaseActivity
import com.backbase.mobileAssignment.ui.base.BaseFragment
import com.backbase.mobileAssignment.utils.SEARCH_DELAY_TIMER
import java.util.*

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var citiesAdapter: CitiesRecyclerAdapter

    private var citiesCount = -1

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
        implementSearch()
        return mBinding.root
    }

    private fun initViews() {
        citiesAdapter = initRecyclerAdapter()
        insertCities()
        observeCitiesLiveData()
    }

    private fun observeCitiesLiveData() {

        mBinding.viewModel?.apply {

            fetchCitiesLiveData.observe(viewLifecycleOwner, {
                citiesCount = it.size
                citiesAdapter.updateList(it)
            })

            insertCitiesLiveData.observe(viewLifecycleOwner, {
                if (it)
                    fetchAllCities() // to fetch cities from DS
                else
                    (activity as? BaseActivity)?.displaySnackBar("Error Fetching cities")
            })
        }
    }

    /**
     * To insert cities from json into our Data Structure
     * */
    private fun insertCities() {
        mBinding.viewModel?.apply {
            insertCities()
        }
    }


    private fun implementSearch() {

        mBinding.etSearch.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                private var timer: Timer = Timer()
                private val DELAY: Long = SEARCH_DELAY_TIMER // milliseconds
                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                if (s.toString().isNotEmpty() && citiesCount >= 0)
                                    mBinding.viewModel?.getSuggestions(s.toString())
                                else if (citiesCount >= 0) {
                                    fetchAllCities()
                                } else
                                    (activity as? BaseActivity)?.displaySnackBar("Please wait until we fetch the cities list")
                            }
                        },
                        DELAY
                    )
                }
            }
        )
    }

    private fun fetchAllCities() {
        mBinding.viewModel?.fetchAllCities()
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
            itemAnimator = null
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(activity)
        }
        return adapter
    }

    interface RecyclerItemClickListener {
        fun onItemClicked(position: Int)
    }

}