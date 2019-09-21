package com.release.spbook.interactors

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import com.release.spbook.presenter.MapPresenterInterface
import com.release.spbook.START_ZOOM
import com.release.spbook.repository.LocationRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class LocationInteractor(val userLocation: LocationRepository, val context: Context) {

    private lateinit var mapPresenter: MapPresenterInterface
    private var compositeDisposable = CompositeDisposable()

    fun attach(mapPresenter: MapPresenterInterface) {
        this.mapPresenter = mapPresenter
    }

     fun getLastKnowLocation() {


         if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

          val a=   userLocation.getLastKnowLocation()!!
             .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())

                 .subscribe {
                     createCameraUpdate(it)
                 }
             compositeDisposable.add(a)
     }
    }

    private fun createCameraUpdate(location: Location){

        val ll = LatLng(location.latitude,location.longitude)
        val update = CameraUpdateFactory.newLatLngZoom(ll, START_ZOOM)
        mapPresenter.setFirstCameraUpdate(update)
    }



    fun dispose(){
        compositeDisposable.dispose()
    }

}