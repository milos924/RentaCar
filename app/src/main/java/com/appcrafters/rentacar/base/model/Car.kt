package com.appcrafters.rentacar.base.model

data class Car(
    val year: Int,
    val id: Int,
    val horsepower: Int,
    val make: String,
    val model: String,
    val price: Int,
    val img_url: String,
)