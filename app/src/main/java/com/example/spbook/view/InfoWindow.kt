package com.example.spbook.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.spbook.entities.POJO.Place
import com.example.spbook.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.info_window.view.*

open class InfoWindow(val context: Context, val place: Place):GoogleMap.InfoWindowAdapter {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val myContentsView = inflater.inflate(R.layout.info_window, null)

    init {
        myContentsView.service_1.setImageResource(place.getService1Icon())
        myContentsView.service_2.setImageResource(place.getService2Icon())
        myContentsView.service_3.setImageResource(place.getService3Icon())
        myContentsView.windowTitle.text = place.title


        if (!place.isService1()) myContentsView.service_1.imageAlpha = 30
        if (!place.isService2()) myContentsView.service_2.imageAlpha = 30
        if (!place.isService3()) myContentsView.service_3.imageAlpha = 30

        myContentsView.windowTimeWork.text = place.timeWork
        myContentsView.windowAddress.text = place.address
    //    val address = myContentsView.findViewById(R.id.windowAddress) as TextView
    //    address.setText(publish.getAddress())
    }


    override fun getInfoContents(p0: Marker?): View {
        return myContentsView
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }
}