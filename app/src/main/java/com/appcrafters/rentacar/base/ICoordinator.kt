package com.appcrafters.rentacar.base

import com.appcrafters.rentacar.base.model.Car

interface ICoordinator {
    fun showDetailsFragment(car: Car)
}