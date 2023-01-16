package com.appcrafters.rentacar.carlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.rentacar.base.data.ICarDataSource
import com.appcrafters.rentacar.base.functional.Either
import com.appcrafters.rentacar.carlist.view.CarListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarListViewModel(private val dataSource: ICarDataSource) : ViewModel() {

    private val _state = MutableLiveData<CarListViewState>()
    val state: LiveData<CarListViewState>
        get() = _state

    fun getCars() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(CarListViewState.Proccessing)

            _state.postValue(
                when (val result = dataSource.getAllCars()) {
                    is Either.Success -> CarListViewState.DataReceived(result.data)
                    is Either.Error -> CarListViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}