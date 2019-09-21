package com.release.spbook.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.release.spbook.*
import com.release.spbook.entities.POJO.Place
import com.release.spbook.R
import com.release.spbook.presenter.MapPresenter
import com.release.spbook.entities.IconMarker
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.appbar_map.*
import javax.inject.Inject
import com.tbruyelle.rxpermissions2.RxPermissions
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.reactivex.disposables.CompositeDisposable


class MapActivity : AppCompatActivity(), OnMapReadyCallback, MapViewInterface {

    lateinit var mapFragment: SupportMapFragment
    @Inject
    lateinit var mapPresenter: MapPresenter
    private val rxPermissions = RxPermissions(this)

     private lateinit var  googleMap : GoogleMap
     private lateinit var clusterManager: ClusterManager<Place>
    private var compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        App.daggerMainComponent.inject(this)
        setSupportActionBar(toolbar_up)
        map.view!!.visibility = View.INVISIBLE
        if(isGooglePlay()) {
            mapPresenter.setListPublish(intent.getParcelableArrayListExtra(PUBLISH_LIST))
            mapPresenter.setListBookStores(intent.getParcelableArrayListExtra(BOOK_STORES_LIST))
            mapPresenter.setListLibraries(intent.getParcelableArrayListExtra(LIBRARIES_LIST))
            mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        }
    }

    override fun asyncMap(){
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {

        this.googleMap = googleMap!!
        clusterManager = ClusterManager(this,googleMap)
       // clusterManager.clearItems()
        mapPresenter.setMarkersUpdate()
        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)
        googleMap.setOnInfoWindowClickListener(clusterManager)
        googleMap.setInfoWindowAdapter(clusterManager.markerManager)
        setMapStyle()

        clusterManager.setOnClusterItemClickListener { p0 ->
            clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindow(this@MapActivity, p0!!))
            false
        }
        clusterManager.setOnClusterItemInfoWindowClickListener {

            clusterManager.clearItems()

            openProfile(it)
        }

        this.googleMap.isMyLocationEnabled = true
    }

    private fun setMapStyle() {
        try {

            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.map_style
                )
            )

            if (!success) {
            }
        } catch (e: Resources.NotFoundException) {
        }


    }


    override fun setMarkersOnMap(list: ArrayList<Place>) {
            clusterManager.addItems(list)
            clusterManager.renderer = IconMarker(this, googleMap, clusterManager)

    }

    override fun onResume() {
        super.onResume()

        val permission =  rxPermissions
            .request(Manifest.permission.ACCESS_FINE_LOCATION)
            .subscribe { granted ->
                if (granted) {
                    mapPresenter.attach(this)
                } else {
                    Toast.makeText(this, R.string.dont_have_location, Toast.LENGTH_LONG).show()
                }
            }

        compositeDisposable.add(permission)
    }


    private fun openProfile(place : Place) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("Place", place)
        startActivity(intent)

    }

    override fun setFirstLocationAndZoom(ll: CameraUpdate) {
        googleMap.moveCamera(ll)
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
            "О приложении"->{
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setCustomAdapter(customAdapter: CustomAdapter) {
        search_view.setAdapter(customAdapter)

        search_view.setOnItemClickListener { parent, view, position, id ->

            openProfile(parent.adapter.getItem(position) as Place)
        }
    }

    override fun onPause() {
        super.onPause()
        if (::clusterManager.isInitialized)
        {
        googleMap.clear()
        clusterManager.clearItems()
        }
    }

    override fun openSettings(status: Status?) {
        status!!.startResolutionForResult(this, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapPresenter.detach()
        compositeDisposable.dispose()
    }

  private fun isGooglePlay():Boolean{
        val googleApiAvailability = GoogleApiAvailability.getInstance();
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;

    }
}
