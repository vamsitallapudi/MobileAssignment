package com.backbase.mobileAssignment.ui.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
            mBinding.tvCoordinates.text = getCoordinates(city)
            mBinding.imageView.setImageDrawable(getCityDrawable(position))
            mBinding.root.setOnClickListener {
                itemClickListener.onItemClicked(city)
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
        private fun getCoordinates(city: City): String {
            return "Coordinates: ${city.coordinates.lat}, ${city.coordinates.lon}"
        }

        private fun getCityDrawable(position: Int): Drawable? {
            val context = mBinding.root.context
            val str = position % 14 + 1
            val resourceId: Int =
                context.resources.getIdentifier("ic_city_$str", "drawable", context.packageName)
            return ContextCompat.getDrawable(mBinding.root.context, resourceId)
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