package com.appcrafters.rentacar.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcrafters.rentacar.R
import com.appcrafters.rentacar.base.model.Car
import com.appcrafters.rentacar.carlist.view.CarListFragment
import com.appcrafters.rentacar.cardetails.view.CarDetailsFragment
import kotlinx.android.synthetic.main.fragment_car_list.*

class MainActivity : AppCompatActivity(), ICoordinator {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showFragment(CarListFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(car: Car) {
        showFragment(CarDetailsFragment.newInstance(car))
    }

}