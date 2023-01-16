package com.appcrafters.rentacar.base.data

object ApiServiceProvider {
    val covidTrackerApiService = RetrofitBuilder.retrofit.create(CarApiService::class.java)
}