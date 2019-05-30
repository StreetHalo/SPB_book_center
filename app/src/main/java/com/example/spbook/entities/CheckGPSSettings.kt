package com.example.spbook.entities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.util.Log
import com.example.spbook.presenter.MapPresenter
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResult
import com.google.android.gms.location.LocationSettingsStatusCodes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

open class CheckGPSSettings(private val locationProvider: ReactiveLocationProvider, private val locationRequest: LocationRequest) {



    fun checkLocationSettings(): Observable<LocationSettingsResult>? {

    return    locationProvider.checkLocationSettings(
            LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true)
                .build()
        )
    }
}