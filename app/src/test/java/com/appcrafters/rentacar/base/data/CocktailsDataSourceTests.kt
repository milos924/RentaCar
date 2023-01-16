package com.appcrafters.rentacar.base.data

import com.appcrafters.rentacar.base.functional.Either
import com.appcrafters.rentacar.base.model.Car
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class CocktailsDataSourceTests {

    @Mock
    lateinit var apiService: CarApiService

    @Mock
    lateinit var getCarsCall: Call<List<Car>>

    lateinit var dataSource: CarDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = CarDataSource(apiService)
    }

    @Test
    fun `testGetCars, has response, Success returned`() = runBlocking {
        val expectedCars: List<Car> = listOf()
        val expectedResult = Either.Success(expectedCars)

        `when`(apiService.getAllCars()).thenReturn(getCarsCall)
        `when`(getCarsCall.execute()).thenReturn(Response.success(expectedCars))

        val result = dataSource.getAllCars()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `testGetCars, has error, Error returned`() = runBlocking {
        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getAllCars()).thenReturn(getCarsCall)
        `when`(getCarsCall.execute()).thenReturn(Response.error(400, expectedResponseBody))


        val result = dataSource.getAllCars()

        assertTrue(result is Either.Error)
    }
}