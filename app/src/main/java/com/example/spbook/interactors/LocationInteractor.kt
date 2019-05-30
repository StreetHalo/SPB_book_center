package com.example.spbook.interactors

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.example.spbook.presenter.MapPresenterInterface
import com.example.spbook.START_ZOOM
import com.example.spbook.repository.LocationRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
             Log.d("MainActivity","2 last know loc")

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