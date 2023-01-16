package com.appcrafters.rentacar.base.data

import com.appcrafters.rentacar.base.model.Car
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CarApiService {

    @GET("cars")
    fun getAllCars(): Call<List<Car>>
}