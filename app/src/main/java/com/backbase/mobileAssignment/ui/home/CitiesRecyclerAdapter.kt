package com.backbase.mobileAssignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.mobileAssignment.R
import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.databinding.CityListItemBinding

class CitiesRecyclerAdapter(
    private val itemClickListener: HomeFragment.RecyclerItemClickListener
) : RecyclerView.Adapter<CitiesRecyclerAdapter.CityViewHolder>() {

    private var cities = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, itemClickListener, position)
    }


    class CityViewHolder(binding: CityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var mBinding: CityListItemBinding = binding
        fun bind(
            city: City,
            itemClickListener: HomeFragment.RecyclerItemClickListener,
            position: Int
        ) {
            mBinding.tvCityTitle.text = getDisplayName(city)
            mBinding.root.setOnClickListener {
                itemClickListener.onItemClicked(position)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CityViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: CityListItemBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.city_list_item,
                        parent,
                        false
                    )
                return CityViewHolder(binding)
            }
        }

        private fun getDisplayName(city: City): String {
            return "${city.name}, ${city.country}"
        }
    }

    fun updateList(newList: List<City>) {
        cities.clear()
        cities.addAll(newList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}