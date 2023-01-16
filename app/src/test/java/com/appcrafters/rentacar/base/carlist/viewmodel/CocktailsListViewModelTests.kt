package com.appcrafters.rentacar.base.cocktailslist.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.rentacar.base.InstantExecutorTest
import com.appcrafters.rentacar.base.data.ICarDataSource
import com.appcrafters.rentacar.base.functional.Either
import com.appcrafters.rentacar.carlist.view.CarListViewState
import com.appcrafters.rentacar.carlist.view.CarListViewState.*
import com.appcrafters.rentacar.base.model.Car
import com.appcrafters.rentacar.carlist.viewmodel.CarListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class CocktailsListViewModelTests : InstantExecutorTest() {
    @Mock
    lateinit var dataSource: ICarDataSource

    @Mock
    lateinit var stateObserver: Observer<CarListViewState>

    lateinit var viewModel: CarListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = CarListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `testGetCars, has result, state changed to Proccessing - DataReceived`() = runBlocking {
        val expectedResult: List<Car> = listOf()

        `when`(dataSource.getAllCars()).thenReturn(Either.Success(expectedResult))

        viewModel.getCars()

        verify(stateObserver).onChanged(Proccessing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `testGetCars, has error, state changed to Proccessing - ErrorReceived`() = runBlocking {
        val expectedError = Exception("test")

        `when`(dataSource.getAllCars()).thenReturn(Either.Error(expectedError))

        viewModel.getCars()

        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}