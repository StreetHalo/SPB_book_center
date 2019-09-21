package com.release.spbook.repository

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.LocationRequest
import io.reactivex.Observable
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

open class LocationRepository(
    private val reactiveLocationProvider: ReactiveLocationProvider,
    private val locationProvider: LocationRequest
) {


    @SuppressLint("MissingPermission")
    fun getLastKnowLocation(): Observable<Location>? {
        return reactiveLocationProvider.getUpdatedLocation(locationProvider)


    }

/*    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Observable<Location>? {

        return reactiveLocationProvider.getUpdatedLocation(locationRequest)

    }*/
}