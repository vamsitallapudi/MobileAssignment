package com.backbase.mobileAssignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.mobileAssignment.R
import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.databinding.CityListItemBinding

class CitiesRecyclerAdapter(
    private val itemClickListener: HomeFragment.RecyclerItemClickListener
) :
    ListAdapter<City, CitiesRecyclerAdapter.StockViewHolder>(STOCKS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city, itemClickListener, position)
    }


    class StockViewHolder(binding: CityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var mBinding: CityListItemBinding = binding
        fun bind(
            city: City,
            itemClickListener: HomeFragment.RecyclerItemClickListener,
            position: Int
        ) {
            mBinding.tvCityTitle.text = city.name
            mBinding.root.setOnClickListener {
                itemClickListener.onItemClicked(position)
            }
        }

        companion object {
            fun create(parent: ViewGroup): StockViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: CityListItemBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.city_list_item,
                        parent,
                        false
                    )
                return StockViewHolder(binding)
            }
        }

    }

    companion object {
        private val STOCKS_COMPARATOR = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}