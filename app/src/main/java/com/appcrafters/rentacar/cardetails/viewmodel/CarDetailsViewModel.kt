package com.appcrafters.rentacar.cardetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.rentacar.base.data.CarDataSource
import com.appcrafters.rentacar.base.functional.Either
import com.appcrafters.rentacar.cardetails.view.CarDetailsViewState
import kotlinx.coroutines.launch

class CarDetailsViewModel(private val dataSource: CarDataSource) : ViewModel() {
    private val _state = MutableLiveData<CarDetailsViewState>()
    val state: LiveData<CarDetailsViewState>
        get() = _state


}