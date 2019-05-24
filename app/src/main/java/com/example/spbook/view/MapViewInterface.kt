package com.example.spbook.view

import android.location.Location
import com.example.spbook.entities.POJO.Place
import com.google.android.gms.maps.CameraUpdate

interface MapViewInterface {

    fun setFirstLocationAndZoom(ll: CameraUpdate)

    fun setMarkersOnMap(list: ArrayList<Place>)

     fun setCustomAdapter(customAdapter: CustomAdapter)

}