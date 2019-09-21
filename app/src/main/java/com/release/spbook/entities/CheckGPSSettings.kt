package com.release.spbook.entities

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResult
import io.reactivex.Observable
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