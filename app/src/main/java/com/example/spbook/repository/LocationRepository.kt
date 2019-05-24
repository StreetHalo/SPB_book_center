package com.example.spbook.repository

import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResult
import com.google.android.gms.location.LocationSettingsStatusCodes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

open class LocationRepository(
  private val  reactiveLocationProvider: ReactiveLocationProvider,
  private val locationRequest: LocationRequest
) {


    fun getLastKnowLocation(): ReactiveLocationProvider {
        return reactiveLocationProvider
    }

/*    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Observable<Location>? {

        return reactiveLocationProvider.getUpdatedLocation(locationRequest)

    }*/

}