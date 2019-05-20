package com.example.spbook.presenter

import android.location.Location
import com.google.android.gms.maps.CameraUpdate

interface MapPresenterInterface {

    fun setFirstCameraUpdate(cameraUpdate: CameraUpdate)

    fun setCurrentLocationUpdate(location: Location)

}