package com.example.spbook.presenter

import android.location.Location
import android.util.Log
import com.example.spbook.POJO.Place
import com.example.spbook.interactors.LocationInteractor
import com.example.spbook.view.MapViewInterface
import com.google.android.gms.maps.CameraUpdate

open class MapPresenter( val locationInteractor: LocationInteractor):
    MapPresenterInterface {

   private var listLibraries = ArrayList<Place>()
   private var listStoreBooks = ArrayList<Place>()
   private var listPublish = ArrayList<Place>()

    private lateinit var  mapView: MapViewInterface

    init {
        locationInteractor.attach(this)
    }

    fun attach(mapView: MapViewInterface){
        this.mapView = mapView
        locationInteractor.getLastKnowLocation()

    }

    fun setListLibraries(listLibraries: ArrayList<Place>){
        this.listLibraries = listLibraries
    }

    fun setListPublish(listPublish: ArrayList<Place>){
        this.listPublish = listPublish
    }

    fun setListBookStores(listStoreBooks: ArrayList<Place>){
        this.listStoreBooks = listStoreBooks
    }

    fun startLocationUpdate(){
        locationInteractor.startUpdateCurrentLocation()
        mapView.setMarkersOnMap(listPublish)
        mapView.setMarkersOnMap(listStoreBooks)
    }

    override fun setFirstCameraUpdate(cameraUpdate: CameraUpdate) {
        mapView.setFirstLocationAndZoom(cameraUpdate)
    }

    override fun setCurrentLocationUpdate(location:Location){
        mapView.updateLocation(location)
        Log.d("LOCATION","lat ${location.latitude}")
    }
}