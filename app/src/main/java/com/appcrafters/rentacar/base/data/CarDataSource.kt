package com.appcrafters.rentacar.base.data

import com.appcrafters.rentacar.base.functional.Either
import com.appcrafters.rentacar.base.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface ICarDataSource {
    suspend fun getAllCars(): Either<List<Car>>
}

class CarDataSource(private val apiService: CarApiService) : ICarDataSource {

    override suspend fun getAllCars(): Either<List<Car>> = handleCall(apiService.getAllCars())

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {
        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }
}