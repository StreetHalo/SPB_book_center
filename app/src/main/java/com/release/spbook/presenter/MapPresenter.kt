package com.release.spbook.presenter

import android.location.Location
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.release.spbook.entities.CheckGPSSettings
import com.release.spbook.entities.POJO.Place
import com.release.spbook.interactors.LocationInteractor
import com.release.spbook.use_cases.PlaceFilter
import com.release.spbook.view.CustomAdapter
import com.release.spbook.view.MapViewInterface
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.maps.CameraUpdate
import io.reactivex.disposables.CompositeDisposable

open class MapPresenter(
    private val locationInteractor: LocationInteractor,
    private val customAdapter: CustomAdapter,
    private val placeFilter: PlaceFilter,
    private val checkGPSSettings: CheckGPSSettings
):
    MapPresenterInterface {
    override fun openGPSsettings(status: Status?) {
        mapView.openSettings(status)
    }
    private var compositeDisposable = CompositeDisposable()
    private var isCameraUpdate = false
    private var listLibraries = ArrayList<Place>()
   private var listStoreBooks = ArrayList<Place>()
   private var listPublish = ArrayList<Place>()

    private lateinit var  mapView: MapViewInterface

    init {
        locationInteractor.attach(this)
    }

    fun attach(mapView: MapViewInterface){
        this.mapView = mapView
       val gps = checkGPSSettings.checkLocationSettings()!!.subscribe {
                locationSettingsResult ->
            val status = locationSettingsResult.status

            when (status.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {

                   openGPSsettings(status)

                }
                LocationSettingsStatusCodes.SUCCESS ->
                {
                    setMap()
                }

            }
        }
        compositeDisposable.add(gps)
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
        placeFilter.setFilter()
        mapView.setMarkersOnMap(placeFilter.filterPublishList(listPublish))
        mapView.setMarkersOnMap(placeFilter.filterStoreListList(listStoreBooks))
        mapView.setMarkersOnMap(placeFilter.filterLibListList(listLibraries))
        mapView.setCustomAdapter(customAdapter)
        getLastKnowPosition()

    }

    override fun setFirstCameraUpdate(cameraUpdate: CameraUpdate) {
        if(!isCameraUpdate) {
            isCameraUpdate = true
            mapView.setFirstLocationAndZoom(cameraUpdate)
        }
    }

    override fun setCurrentLocationUpdate(location:Location){
        //mapView.updateLocation(location)
    }



    fun detach(){
        locationInteractor.dispose()
        compositeDisposable.dispose()
    }

    private fun setMap() {
        mapView.asyncMap()
    }

  private fun getLastKnowPosition(){
        locationInteractor.getLastKnowLocation()

    }

}