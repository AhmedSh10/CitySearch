package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCityBinding
import com.example.myapplication.models.City

class CitiesAdapter(private val cities: List<City>, private val clickListener: (City) -> Unit) :
    RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, clickListener)
    }

    override fun getItemCount() = cities.size

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(android.R.id.text1)
        private val subtitle = itemView.findViewById<TextView>(android.R.id.text2)

        fun bind(city: City, clickListener: (City) -> Unit) {
            title.text = "${city.name}, ${city.country}"
            subtitle.text = "Coordinates: (${city.coord.lat}, ${city.coord.lon})"
            itemView.setOnClickListener { clickListener(city) }
        }
    }
}
