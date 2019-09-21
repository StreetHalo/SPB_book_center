package com.release.spbook.DI.module

import android.content.Context
import com.release.spbook.INTERVAL_LOCATION_UPDATE
import com.release.spbook.NUM_UPDATE
import com.release.spbook.entities.CheckGPSSettings
import com.release.spbook.presenter.MapPresenter
import com.release.spbook.interactors.LocationInteractor
import com.release.spbook.repository.LocationRepository
import com.release.spbook.use_cases.PlaceFilter
import com.release.spbook.view.CustomAdapter
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import javax.inject.Singleton
import com.google.android.gms.location.LocationRequest

@Module
open class MapModule(val context:Context) {

    @Provides
     fun provideLocationProvider(): ReactiveLocationProvider {
        return object : ReactiveLocationProvider(context){}
    }
    @Singleton
    @Provides
    fun provideLocationRequest(): LocationRequest {
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(INTERVAL_LOCATION_UPDATE)
            .setNumUpdates(NUM_UPDATE)

        return locationRequest
        }
    @Singleton
    @Provides
    fun provideUserLocation(reactiveLocationProvider: ReactiveLocationProvider, locationRequest: LocationRequest): LocationRepository {

        return object : LocationRepository(reactiveLocationProvider,locationRequest){}
    }

    @Provides
    fun provideLocationInterator(userLocation: LocationRepository): LocationInteractor {

        return object : LocationInteractor(userLocation,context){}
    }

    @Provides
    fun provideMapPresenter(locationInteractor: LocationInteractor,
                            customAdapter: CustomAdapter,
                            placeFilter: PlaceFilter,
                            checkGPSSettings: CheckGPSSettings): MapPresenter {

        return object : MapPresenter(locationInteractor, customAdapter,placeFilter,checkGPSSettings){}
    }

    @Provides
    fun provideCustomAdapter(): CustomAdapter {
        return object : CustomAdapter(context){}
    }

    @Provides
    fun providePlaceFilter(): PlaceFilter {
        return object :PlaceFilter(context){}
    }

    @Provides
    fun provideCheckGPS(reactiveLocationProvider: ReactiveLocationProvider, locationRequest: LocationRequest): CheckGPSSettings {
        return object :CheckGPSSettings(reactiveLocationProvider, locationRequest){}
    }

}