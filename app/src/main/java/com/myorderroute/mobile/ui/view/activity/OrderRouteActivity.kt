/*
 * OrderRouteActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.myorderroute.mobile.R
import com.myorderroute.mobile.databinding.ActivityOrderRouteBinding
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.domain.state.OrderStatus
import com.myorderroute.mobile.ui.view.dialog.SuccessDialogConfig
import com.myorderroute.mobile.ui.viewModel.OrderRouteUiEvent
import com.myorderroute.mobile.ui.viewModel.OrderRouteUiState
import com.myorderroute.mobile.ui.viewModel.OrderRouteViewModel
import com.myorderroute.mobile.util.collect
import com.myorderroute.mobile.util.gone
import com.myorderroute.mobile.util.materialDialog
import com.myorderroute.mobile.util.show
import com.myorderroute.mobile.util.toast
import com.myorderroute.mobile.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderRouteActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOrderRouteBinding::inflate)

    private val viewModel: OrderRouteViewModel by viewModels()

    private lateinit var googleMap: GoogleMap
    private var currentLocationMarker: Marker? = null
    private var nextLocationMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInit()
    }

    private fun setInit() {
        initMap()
        setListeners()
    }

    private fun initMap() {
        (supportFragmentManager.findFragmentById(R.id.mapFragmentLayout) as SupportMapFragment)
            .getMapAsync { map ->
                googleMap = map
                viewModel.loadRoute()
                setFlows()
            }
    }

    private fun setListeners() {
        binding.deliverButton.setOnClickListener {
            showDeliveryDialog()
        }
    }

    private fun setFlows() {
        collect(viewModel.orderRouteUiState) { state ->
            statusLoading(state.isLoading)
            validateData(state)
        }
        collect(viewModel.orderRouteUiEvent) { event ->
            if (event is OrderRouteUiEvent.Error) {
                toast(event.message)
            }
        }
    }

    private fun statusLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) orderRouteProgressBar.show()
        else orderRouteProgressBar.gone()
    }

    private fun validateData(state: OrderRouteUiState) {
        if (state.pendingLocations.isNotEmpty()) {
            goneEmptyState()
            validateCompletedOrders()
            setCurrentLocation(currentLocation = state.currentLocation)
            setNextLocation(nextLocation = state.nextLocation)
        } else {
            showEmptyState()
        }
    }

    private fun setCurrentLocation(currentLocation: CoordinatesModel?) {
        currentLocation?.let { coordinates ->
            currentLocationMarker?.remove()
            val current = LatLng(coordinates.latitude, coordinates.longitude)
            currentLocationMarker = googleMap.addMarker(
                MarkerOptions()
                    .position(current)
                    .title(getString(R.string.current_location))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)),
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 12f))
        }
    }

    private fun setNextLocation(nextLocation: CoordinatesModel?) {
        nextLocation?.let { coordinates ->
            nextLocationMarker?.remove()
            val next = LatLng(coordinates.latitude, coordinates.longitude)
            nextLocationMarker = googleMap.addMarker(
                MarkerOptions()
                    .position(next)
                    .title(getString(R.string.next_order))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)),
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(next, 12f))
            setLocationTitle(locationName = nextLocation.name)
        }
    }

    private fun setLocationTitle(locationName: String?) = with(binding) {
        nextLocationNameTextView.text = locationName ?: getString(R.string.n_a)
        nextLocationCard.show()
    }

    private fun validateCompletedOrders() {
        val allCompleted = viewModel.orderRouteUiState.value.pendingLocations.all { location ->
            location.status == OrderStatus.COMPLETED
        }
        if (allCompleted) {
            showSuccessDialog()
        }
    }

    private fun showDeliveryDialog() {
        materialDialog(
            style = R.style.MaterialDialog,
            title = getString(R.string.message),
            message = getString(R.string.are_you_sure_you_want_to_deliver_this_order),
            textNegativeButton = getString(R.string.cancel),
            textPositiveButton = getString(R.string.accept),
        ) {
            viewModel.deliverOrder()
        }
    }

    private fun showSuccessDialog() {
        SuccessDialogConfig().also { config ->
            config.apply {
                showDialog(supportFragmentManager)
                setCancelable(false)
            }
        }
    }

    private fun showEmptyState() = with(binding) {
        orderEmptyState.show()
        mapConstraintLayout.gone()
    }

    private fun goneEmptyState() = with(binding) {
        orderEmptyState.gone()
        mapConstraintLayout.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::googleMap.isInitialized) {
            googleMap.clear()
        }
    }
}
