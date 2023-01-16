package com.appcrafters.rentacar.carlist.view

import com.appcrafters.rentacar.base.model.Car

sealed class CarListViewState {

    object Proccessing : CarListViewState()
    data class DataReceived(val cars: List<Car>) :CarListViewState()
    data class ErrorReceived(val message: String) : CarListViewState()
}