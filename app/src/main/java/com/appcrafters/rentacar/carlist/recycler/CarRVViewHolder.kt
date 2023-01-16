package com.appcrafters.rentacar.carlist.recycler

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.rentacar.R
import com.appcrafters.rentacar.base.model.Car
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_car_list.view.*
import kotlinx.android.synthetic.main.item_car.view.*

class CarRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(car: Car, onItemClicked: (Car) -> Unit) {
        itemView.idTxt.text = car.id.toString()
        itemView.carModelTxt.text = car.model

        Glide.with(itemView).load(car.img_url).error(R.drawable.noimageplaceholder).into(itemView.carImg)
        itemView.priceTxt.text = (car.price/1000).toString()+"$"

        itemView.setOnClickListener {
            onItemClicked.invoke(car)
        }
    }
}