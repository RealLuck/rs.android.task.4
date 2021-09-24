package com.realluck.roomwordsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.realluck.roomwordsample.model.City

class CityListAdapter : ListAdapter<City, CityListAdapter.CityViewHolder>(CityComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityItemView: TextView = itemView.findViewById(R.id.city_textView)
        private val countryItemView: TextView = itemView.findViewById(R.id.county_textView)
        private val capacityItemView: TextView = itemView.findViewById(R.id.capacity_textView)

        fun bind(city: City) {
            cityItemView.text = city.city
            countryItemView.text = city.country
            capacityItemView.text = city.capacity.toString()
        }

        companion object {
            fun create(parent: ViewGroup): CityViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CityViewHolder(view)
            }
        }
    }

    class CityComparator : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.city == newItem.city
        }
    }
}

