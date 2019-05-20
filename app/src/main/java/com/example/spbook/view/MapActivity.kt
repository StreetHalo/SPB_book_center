package com.example.spbook.view

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.spbook.App
import com.example.spbook.BOOK_STORES_LIST
import com.example.spbook.POJO.Place
import com.example.spbook.PUBLISH_LIST
import com.example.spbook.presenter.MapPresenter
import com.example.spbook.R
import com.example.spbook.entities.IconMarker
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.appbar_map.*
import javax.inject.Inject

class MapActivity : AppCompatActivity(), OnMapReadyCallback, MapViewInterface {


    @Inject
     lateinit var mapPresenter: MapPresenter
     private lateinit var  googleMap : GoogleMap
     private lateinit var clusterManager: ClusterManager<Place>
     private lateinit var markerPosition : Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        App.daggerMainComponent.inject(this)
        setSupportActionBar(toolbar_up)
        map.view!!.visibility = View.INVISIBLE
     mapPresenter.setListPublish(intent.getParcelableArrayListExtra(PUBLISH_LIST))
        mapPresenter.setListBookStores(intent.getParcelableArrayListExtra(BOOK_STORES_LIST))
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap!!
        clusterManager = ClusterManager(this,googleMap)

        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)
        googleMap.setOnInfoWindowClickListener(clusterManager)
        googleMap.setInfoWindowAdapter(clusterManager.markerManager)
        googleMap.isMyLocationEnabled = true
    }




    override fun updateLocation(location: Location) {
     // if(!map.isVisible)

    }

    override fun setMarkersOnMap(list: ArrayList<Place>) {
            clusterManager.addItems(list)
            clusterManager.renderer = IconMarker(this, googleMap, clusterManager!!)
            clusterManager.setOnClusterItemClickListener { p0 ->
                clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindow(this@MapActivity, p0!!))
                false
            }
            clusterManager.setOnClusterItemInfoWindowClickListener {
                openProfile(it)
            }


    }

    override fun onResume() {
        super.onResume()
        mapPresenter.attach(this)
    }


    private fun openProfile(place : Place) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("Place", place)
        startActivity(intent)
    }

    override fun setFirstLocationAndZoom(update: CameraUpdate) {
        googleMap.moveCamera(update)
        mapPresenter.startLocationUpdate()
        map.view!!.visibility = View.VISIBLE

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        val item = menu!!.findItem(R.id.action_search)
        search_view.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item.toString()){
            "Фильтр"->{
                val intentFilter = Intent(this, FilterActivity::class.java)
                startActivity(intentFilter)
            }


        }
        return super.onOptionsItemSelected(item)
    }
}
