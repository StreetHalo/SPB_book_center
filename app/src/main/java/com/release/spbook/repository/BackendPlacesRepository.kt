package com.release.spbook.repository

import com.release.spbook.entities.POJO.BookStore
import com.release.spbook.entities.POJO.Library
import com.release.spbook.entities.POJO.Publish
import io.reactivex.Observable
import retrofit2.Retrofit

class BackendPlacesRepository(retrofit: Retrofit) {
    private val placesApi = retrofit.create(PlacesApi::class.java)


    fun getPublishPlaces(): Observable<ArrayList<Publish>> {
       return placesApi.getPublishList()
    }

    fun getStorePlaces(): Observable<ArrayList<BookStore>> {
        return placesApi.getBookStoreList()
    }

    fun getLibPlaces():Observable<ArrayList<Library>>{
        return placesApi.getLibsList()
    }
}