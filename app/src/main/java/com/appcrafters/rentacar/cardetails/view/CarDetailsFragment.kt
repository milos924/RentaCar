package com.appcrafters.rentacar.cardetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.rentacar.R
import com.appcrafters.rentacar.base.data.ApiServiceProvider
import com.appcrafters.rentacar.base.data.CarDataSource
import com.appcrafters.rentacar.base.functional.ViewModelFactoryUtil
import com.appcrafters.rentacar.base.model.Car
import com.appcrafters.rentacar.cardetails.viewmodel.CarDetailsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_car_details.*
import kotlinx.android.synthetic.main.item_car.*
import kotlinx.android.synthetic.main.item_car.view.*
import java.util.*

class CarDetailsFragment : Fragment() {

    private lateinit var _viewModel: CarDetailsViewModel

    companion object {

        private var CAR: Car = Car(1, 1, 1, "", "25", 1, "")

        fun newInstance(car: Car): CarDetailsFragment {
            return CarDetailsFragment().apply {
                CAR = car
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            CarDetailsViewModel(CarDataSource(ApiServiceProvider.covidTrackerApiService))
        }).get(CarDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel(CAR)
    }

    private fun bindFromViewModel(car: Car) {
        setUpView(car)

    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(data: Car) {
        Glide.with(this).load(data.img_url).error(R.drawable.noimageplaceholder).into(wallpaperImg)

        makeTxt.text = data.make.replaceFirstChar(Char::titlecase)
        modelTxt.text = data.model + " " + data.year
        price2Txt.text = "" + (data.price / 1000) + "$"

        var msg: String = "Your request for reservation from 3/2/2022"
        var msg2: String = ", to 3/2/2022 has been made!"

        val today = Calendar.getInstance()

        datePickerFrom.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, month, day ->
            val month = month + 1
            msg = "Your request for reservation from $day/$month/$year"
        }

        datePickerTo.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, month, day ->
            val month = month + 1
            msg2 = ", to $day/$month/$year has been made!"
        }

        submitBtn.setOnClickListener {
            Toast.makeText(requireContext(), msg+msg2, Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }


    }
}
