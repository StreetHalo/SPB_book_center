package com.example.spbook.view

import android.location.Location
import com.example.spbook.POJO.Place
import com.google.android.gms.maps.CameraUpdate

interface MapViewInterface {
    fun updateLocation(location: Location)

    fun setFirstLocationAndZoom(ll: CameraUpdate)

    fun setMarkersOnMap(list: ArrayList<Place>)

}