package com.appcrafters.rentacar.carlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.appcrafters.rentacar.R
import com.appcrafters.rentacar.base.model.Car

class CarRVAdapter(private val cars: List<Car>, private val onItemClicked: (Car) -> Unit) :
    Adapter<CarRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
    )

    override fun onBindViewHolder(holder: CarRVViewHolder, position: Int) {
        holder.bind(cars[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return cars.size
    }
}