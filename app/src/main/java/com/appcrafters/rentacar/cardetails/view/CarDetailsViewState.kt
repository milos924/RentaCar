package com.appcrafters.rentacar.cardetails.view

import com.appcrafters.rentacar.base.model.Car

sealed class CarDetailsViewState {
    object Proccessing: CarDetailsViewState()
    data class DataReceived(val car: Car) : CarDetailsViewState()
    data class ErrorReceived(val message: String) : CarDetailsViewState()
}