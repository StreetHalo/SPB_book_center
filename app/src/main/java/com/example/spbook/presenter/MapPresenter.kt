package com.example.spbook.presenter

import android.location.Location
import android.util.Log
import com.example.spbook.entities.POJO.Place
import com.example.spbook.interactors.LocationInteractor
import com.example.spbook.use_cases.PlaceFilter
import com.example.spbook.view.CustomAdapter
import com.example.spbook.view.MapViewInterface
import com.google.android.gms.maps.CameraUpdate

open class MapPresenter(
    val locationInteractor: LocationInteractor,
    val  customAdapter: CustomAdapter,
    val placeFilter: PlaceFilter
):
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
        customAdapter.addList(listLibraries)
    }

    fun setListPublish(listPublish: ArrayList<Place>){
        this.listPublish = listPublish
        customAdapter.addList(listPublish)
    }

    fun setListBookStores(listStoreBooks: ArrayList<Place>){
        this.listStoreBooks = listStoreBooks
        customAdapter.addList(listStoreBooks)
    }

    fun setMarkersUpdate(){
       // locationInteractor.startUpdateCurrentLocation()
        placeFilter.setFilter()
        mapView.setMarkersOnMap(placeFilter.filterPublishList(listPublish))
        mapView.setMarkersOnMap(placeFilter.filterStoreListList(listStoreBooks))
        mapView.setMarkersOnMap(placeFilter.filterLibListList(listLibraries))
        mapView.setCustomAdapter(customAdapter)
    }

    override fun setFirstCameraUpdate(cameraUpdate: CameraUpdate) {
        mapView.setFirstLocationAndZoom(cameraUpdate)
    }

    override fun setCurrentLocationUpdate(location:Location){
        //mapView.updateLocation(location)
        Log.d("LOCATION","lat ${location.latitude}")
    }
}