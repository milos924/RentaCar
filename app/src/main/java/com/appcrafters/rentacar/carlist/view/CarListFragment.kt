package com.appcrafters.rentacar.carlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.rentacar.R
import com.appcrafters.rentacar.base.ICoordinator
import com.appcrafters.rentacar.base.data.ApiServiceProvider
import com.appcrafters.rentacar.base.data.CarDataSource
import com.appcrafters.rentacar.base.functional.ViewModelFactoryUtil
import com.appcrafters.rentacar.base.model.Car
import com.appcrafters.rentacar.carlist.recycler.CarRVAdapter
import com.appcrafters.rentacar.carlist.viewmodel.CarListViewModel
import kotlinx.android.synthetic.main.fragment_car_list.*

class CarListFragment : Fragment() {
    private lateinit var viewModel: CarListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            CarListViewModel(CarDataSource(ApiServiceProvider.covidTrackerApiService))
        }).get(CarListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getCars()
    }

    private fun setUpRecyclerView(cars: List<Car>) {
        carListRV.adapter = CarRVAdapter(cars) { car ->
            (activity as ICoordinator).showDetailsFragment(car)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            carListProgressBar.isVisible = state is CarListViewState.Proccessing

            when (state) {
                is CarListViewState.DataReceived -> {
                    setUpRecyclerView(state.cars)
                }
                is CarListViewState.ErrorReceived -> showError(state.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}