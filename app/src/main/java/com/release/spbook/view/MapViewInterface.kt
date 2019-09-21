package com.release.spbook.view

import com.release.spbook.entities.POJO.Place
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdate

interface MapViewInterface {

    fun setFirstLocationAndZoom(ll: CameraUpdate)

    fun setMarkersOnMap(list: ArrayList<Place>)

     fun setCustomAdapter(customAdapter: CustomAdapter)

    fun openSettings(status: Status?)

    fun asyncMap()

}